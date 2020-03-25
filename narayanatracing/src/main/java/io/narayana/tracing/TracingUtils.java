package io.narayana.tracing;

import static io.narayana.tracing.names.StringConstants.NARAYANA_COMPONENT_NAME;
import static io.narayana.tracing.names.StringConstants.TRACING_ACTIVATED_SYSPROP_NAME;

import java.util.Objects;
import java.util.Optional;

import io.narayana.tracing.names.SpanName;
import io.narayana.tracing.names.TagName;
import io.narayana.tracing.names.TransactionStatus;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 *
 * Instead of accessing the tracing code directly, much of the work is done
 * "behind the scenes" in this class and the intention is to provide as thin API
 * to the user as possible.
 *
 * Most of the opentracing functionality is to be accessed from this utility
 * class.
 *
 * Note: we're not using the Uid class as a key as this would create a cyclic dependency
 * between narayanatracing and other arjuna modules. Strings (which should always
 * represent a Uid!) are used instead.
 *
 * Note: spans are always activated at the point of span creation (we tightly
 * couple the events again because of the goal of having a thin API).
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public class TracingUtils {
    static final boolean TRACING_ACTIVATED = Boolean
            .valueOf(System.getProperty(TRACING_ACTIVATED_SYSPROP_NAME, "true"));
    private static final Scope DUMMY_SCOPE = () -> {};

    private TracingUtils() {
    }

    public static Scope activateSpan(Span span) {
        if (!TRACING_ACTIVATED) return DUMMY_SCOPE;
        return getTracer().activateSpan(span);
    }

    /**
     * Starts a new root span of a trace representing one distributed transaction.
     *
     * @param txUid string representation of the transaction
     * @return root span of the transaction
     */
    public static Span start(String txUid) {
        Objects.requireNonNull(txUid);
        return new RootSpanBuilder().build(txUid);
    }

    public static Span startSubordinate(Class<?> cl, String txUid) {
        Objects.requireNonNull(txUid);
        return new RootSpanBuilder().tag(TagName.TXINFO, cl).subordinate().build(txUid);
    }

    /**
     * Build a new root span which represents the whole transaction. Any
     * other span handles created in the Narayana code base should be attached to
     * this root scope using the "ordinary" SpanBuilder.
     *
     */
    private static class RootSpanBuilder {

        private SpanBuilder spanBldr;

        RootSpanBuilder() {
            if(!TRACING_ACTIVATED) return;
            spanBldr = prepareSpan(SpanName.TX_ROOT).withTag(Tags.ERROR, false);
        }

        private static SpanBuilder prepareSpan(SpanName name) {
            Objects.requireNonNull(name, "Name of the span cannot be null");
            return getTracer().buildSpan(name.toString());
        }

        /**
         * Adds tag to the started span.
         */
        public RootSpanBuilder tag(TagName name, Object val) {
            if(!TRACING_ACTIVATED) return this;
            Objects.requireNonNull(name, "Name of the tag cannot be null");
            spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
            return this;
        }

        /**
         * Mark this span as a subordinate txn.
         * @return
         */
        public RootSpanBuilder subordinate() {
            spanBldr = spanBldr.withTag("subordinate", "true");
            return this;
        }

        /**
         * Build the root span.
         *
         * @throws IllegalArgumentException {@code txUid} is null or a span with this ID
         *                                  already exists
         * @param txUid UID of the new transaction
         * @return root span of the new transaction
         */
        public Span build(String txUid) {
            if(!TRACING_ACTIVATED) return null;
            tag(TagName.UID, txUid);
            return spanBldr.withTag(Tags.COMPONENT, NARAYANA_COMPONENT_NAME).start();
        }
    }

    /**
     * Mark the span itself as failed in terms of opentracing.
     * Hence this is different from setting the transaction
     * status span tag as failed via calling {@code setTransactionStatus}.
     */
    public static void markTransactionFailed(Span rootSpan) {
        if (!TRACING_ACTIVATED || rootSpan == null) return;
        rootSpan.setTag(Tags.ERROR, true);
    }

    /**
     * Sets TagName.STATUS tag of the root span. If this method is called more than
     * once, the value is overwritten.
     *
     * @throws IllegalArgumentException {@code txUid} does not represent any
     *                                  currently managed transaction
     * @param rootSpan
     * @param status one of the possible states any transaction could be in
     */
    public static void setTransactionStatus(Span rootSpan, TransactionStatus status) {
        if (!TRACING_ACTIVATED || rootSpan == null) return;
        rootSpan.setTag(TagName.STATUS.toString(), status.toString().toLowerCase());
    }

    /**
     * Sets tag which for a span which is currently activated by the scope manager.
     * Useful when a user wishes to add tags whose existence / value is dependent on
     * the context (i.e. status of the transaction inside of the method call).
     */
    public static void addTag(TagName name, String val) {
        if (!TRACING_ACTIVATED) return;
        activeSpan().ifPresent(s -> s.setTag(name.toString(), val));
    }

    /**
     * Log a message for the currently active span.
     */
    public static void log(String message) {
        if (!TRACING_ACTIVATED) return;
        activeSpan().ifPresent(s -> s.log(message));
    }

    static Optional<Span> activeSpan() {
        if (!TRACING_ACTIVATED) return Optional.empty();
        Span span = getTracer().activeSpan();
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * @return registered tracer or any default tracer provided by the opentracing
     *         implementation
     */
    static Tracer getTracer() {
        // when tracing is deactivated,
        // no tracer code should be called
        if (!TRACING_ACTIVATED) return null;
        return GlobalTracer.get();
    }
}
