package io.narayana.tracing;

import java.util.Collections;
import java.util.Optional;

import io.narayana.tracing.names.TagName;
import io.narayana.tracing.names.TransactionStatus;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tag;
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
 * Three classes should be taken into consideration: i) SpanHandle, an
 * abstraction of the OpenTracing span providing only the {@code close} method
 * with which the user declares that the span is no longer active and should be
 * reported; all the other functionality is provided via static methods of this
 * class
 *
 * ii) RootSpanHandleBuilder, responsible for building a SpanHandle which
 * represents the root of a transaction trace, all other spans created under
 * Narayana will be (direct or indirect) children of this span
 *
 * iii) SpanHandleBuilder - responsible for building regular spans
 *
 * Note: spans are always activated at the point of span creation (we tightly
 * couple the events again because of the goal of having a thin API).
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public class TracingUtils {
    static final boolean TRACING_ACTIVATED = Boolean
            .valueOf(System.getProperty("org.jboss.narayana.tracingActivated", "true"));
    static final Scope DUMMY_SCOPE = new DummyScope();

    private TracingUtils() {
    }

    public static Scope activateSpan(Span span) {
        if (!TRACING_ACTIVATED) return DUMMY_SCOPE;
        return getTracer().activateSpan(span);
    }

    /*
     * This method switches from the "pre-2PC" phase to the protocol phase.
     *
     * It is possible (and highly probable) that this method will be called more
     * than once during a transaction's existence.
     *
     * Therefore, we always must explicitly check that if a root span with {@code
     * txUid} still exists but there is none corresponding in the pre2pc registry,
     * we must have already called therefore can simply ignore such calls.
     */
    public static void begin2PC(String txUid) {
        if (!TRACING_ACTIVATED) return;
        SpanRegistry.removePre2pc(txUid).ifPresent(s -> s.finish());
        if (!SpanRegistry.getRoot(txUid).isPresent()) {
            throw new IllegalStateException(String.format("Pre2PC span with id '%s' was deactivated but no corresponding parent span found.", txUid));
        }
    }

    private static void finish(String txUid, boolean remove) {
        if (!TRACING_ACTIVATED) return;
        // We need to check for superfluous calls to this method
        Optional<Span> span = remove ? SpanRegistry.removeRoot(txUid) : SpanRegistry.getRoot(txUid);
        span.ifPresent(s -> s.finish());
    }

    /**
     * Finishes the root span representing the transaction with id {@code txUid}
     *
     * @param txUid
     */
    public static void finish(String txUid) {
        if (!TRACING_ACTIVATED) return;
        finish(txUid, true);
    }

    /**
     * Finishes the root span but still keeps it in the collection, making it
     * possible to attach async spans (which are outside of the reach of the trace).
     *
     * @param txUid
     */
    public static void finishWithoutRemoval(String txUid) {
        if (!TRACING_ACTIVATED) return;
        finish(txUid, false);
    }

    /**
     * This is different from setting the transaction status as failed. Using this
     * method, the span itself in terms of opentracing is marked as failed.
     *
     */
    public static void markTransactionFailed(String txUid) {
        if (!TRACING_ACTIVATED) return;
        SpanRegistry.getRoot(txUid).ifPresent(s -> s.setTag(Tags.ERROR, true));
    }

    /**
     * Sets TagName.STATUS tag of the root span. If this method is called more than
     * once, the value is overwritten.
     *
     * @throws IllegalArgumentException {@code txUid} does not represent any
     *                                  currently managed transaction
     * @param txUid  UID of the transaction
     * @param status one of the possible states any transaction could be in
     */
    public static void setTransactionStatus(String txUid, TransactionStatus status) {
        if (!TRACING_ACTIVATED) return;
        SpanRegistry.getRoot(txUid)
                .ifPresent(s -> s.setTag(TagName.STATUS.toString(), status.toString().toLowerCase()));
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

    public static void addTag(TagName name, Object obj) {
        if (!TRACING_ACTIVATED) return;
        addTag(name, obj == null ? "null" : obj.toString());
    }

    public static <T> void addTag(Tag<T> tag, T obj) {
        if (!TRACING_ACTIVATED) return;
        activeSpan().ifPresent((s) -> s.setTag(tag, obj));
    }

    /**
     * Log a message for the currently active span.
     */
    public static void log(String message) {
        if (!TRACING_ACTIVATED) return;
        activeSpan().ifPresent(s -> s.log(message));
    }

    public static <T> void log(String fld, String value) {
        if (!TRACING_ACTIVATED) return;
        activeSpan().ifPresent(s -> s.log(Collections.singletonMap(fld, value)));
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
    public static Tracer getTracer() {
        // when tracing is deactivated,
        // no tracer code should be called
        if (!TRACING_ACTIVATED) return null;
        return GlobalTracer.get();
    }
}
