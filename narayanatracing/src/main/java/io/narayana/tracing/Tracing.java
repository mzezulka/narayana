package io.narayana.tracing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tag;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 *
 */
public class Tracing {

    private static final Map<String, Span> TX_UID_TO_SPAN_MAP = new HashMap<>();

    /**
     * Create a new root span which represents the whole transaction.
     * Any other spans created in the Narayana code base should be attached to this root scope
     * using the "ordinary" ScopeBuilder.
     *
     * Usage: as the transaction will always begin and start at two different methods, we need to manage (de)activation of
     * the span manually, schematically like this:
     * <pre>
     * <code>public void transactionStart(Uid uid, ...) {
     *     new ScopeBuilder(SpanName.TX_BEGIN, uid)
     *            ...
     *           .startRootSpan(uid);
     *     ...
     *}
     *
     *public void transactionEnd(Uid uid, ...) {
     *     TracingUtils.finish(uid);
     *     ...
     * </code>}
     * </pre>
     */
    public static class RootScopeBuilder {

        private SpanBuilder spanBldr;

        public RootScopeBuilder(Object...args) {
            this.spanBldr = prepareSpan(SpanName.TX_ROOT, args);
        }

        private static SpanBuilder prepareSpan(SpanName name, Object... args) {
            Objects.requireNonNull(name, "Name of the span cannot be null");
            return getTracer().buildSpan(String.format(name.toString(), args));
        }

        /**
         * Adds tag to the started span.
         */
        public RootScopeBuilder tag(TagName name, Object val) {
            Objects.requireNonNull(name, "Name of the tag cannot be null");
            spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
            return this;
        }

        public <T> RootScopeBuilder tag(Tag<T> tag, T value) {
            Objects.requireNonNull(tag, "Tag cannot be null.");
            spanBldr = spanBldr.withTag(tag, value);
            return this;
        }

        public Scope start(String txUid) {
            spanBldr.asChildOf(TX_UID_TO_SPAN_MAP.get(txUid));
            return getTracer().scopeManager().activate(spanBldr.withTag(Tags.COMPONENT, "narayana").start());
        }

        /**
         * @throws IllegalArgumentException {@code txUid} is null or a span with this ID already exists
         * @param txUid UID of the new transaction
         * @return
         */
        public Scope startRootSpan(String txUid) {
            Span span = spanBldr.withTag(Tags.COMPONENT, "narayana").ignoreActiveSpan().start();
            TX_UID_TO_SPAN_MAP.put(txUid, span);
            return getTracer().scopeManager().activate(span);
        }
    }

    /**
     * Responsibility of this class: create a new span and activate it under scope of one root scope representing
     * the transaction.
     * <pre>
     * <code>try (Scope s = new ScopeBuilder(SpanName.TX_BEGIN).start()) {
     *     // this is where 's' is active
     * } finally {
     *     TracingUtils.finishActiveSpan();
     * }
     * </code>
     * </pre>
     */
    public static class ScopeBuilder {

        private SpanBuilder spanBldr;
        /**
         * @param name name of the span which will be activated in the scope
         */
        public ScopeBuilder(SpanName name, Object...args) {
            this.spanBldr = prepareSpan(name, args);
        }

        private static SpanBuilder prepareSpan(SpanName name, Object... args) {
            Objects.requireNonNull(name, "Name of the span cannot be null");
            return getTracer().buildSpan(String.format(name.toString(), args));
        }

        /**
         * Adds tag to the started span.
         */
        public ScopeBuilder tag(TagName name, Object val) {
            Objects.requireNonNull(name, "Name of the tag cannot be null");
            spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
            return this;
        }

        public <T> ScopeBuilder tag(Tag<T> tag, T value) {
            Objects.requireNonNull(tag, "Tag cannot be null.");
            spanBldr = spanBldr.withTag(tag, value);
            return this;
        }

        public Scope start(String txUid) {
            spanBldr.asChildOf(TX_UID_TO_SPAN_MAP.get(txUid));
            return getTracer().scopeManager().activate(spanBldr.withTag(Tags.COMPONENT, "narayana").start());
        }

        /**
         * @throws IllegalArgumentException {@code txUid} is null or a span with this ID already exists
         * @param txUid UID of the new transaction
         * @return
         */
        public Scope startRootSpan(String txUid) {
            Span span = spanBldr.withTag(Tags.COMPONENT, "narayana").ignoreActiveSpan().start();
            TX_UID_TO_SPAN_MAP.put(txUid, span);
            return getTracer().scopeManager().activate(span);
        }
    }

    private Tracing() {
    }

    /**
     * Finishes root span representing the transaction with id {@code txUid}
     * @param txUid
     */
    public static void finish(String txUid) {
        TX_UID_TO_SPAN_MAP.get(txUid).finish();
    }

    /**
     * This is different from setting the transaction status as failed.
     * Using this method, the span itself is marked as failed (in terms of
     * the OpenTracing API).
     * @param txUid
     */
    public static void markTransactionFailed(String txUid) {
        TX_UID_TO_SPAN_MAP.get(txUid).setTag(Tags.ERROR, true);
    }

    /**
     * Sets TagName.STATUS tag of the root span. If this method is called more than once,
     * the value is overwritten.
     * @throws IllegalArgumentException {@code txUid} does not represent any currently managed transaction
     * @param txUid UID of the transaction
     * @param status one of the possible states any transaction could be in
     */
    public static void setTransactionStatus(String txUid, TransactionStatus status) {
        TX_UID_TO_SPAN_MAP.get(txUid).setTag(TagName.STATUS.toString(), status.toString().toLowerCase());
    }

    /**
     * Sets tag which is currently activated by the scope manager.
     * Useful when the user wishes to add tags whose existence / value
     * is dependent on the context (i.e. status of the transaction).
     */
    public static void addActiveSpanTag(TagName name, String val) {
        activeSpan().ifPresent(s -> s.setTag(name.toString(), val));
    }

    public static void addCurrentSpanTag(TagName name, Object obj) {
        addActiveSpanTag(name, obj == null ? "null" : obj.toString());
    }

    public static <T> void addCurrentSpanTag(Tag<T> tag, T obj) {
        activeSpan().ifPresent((s) -> s.setTag(tag, obj));
    }

    /**
     * Log a message for the currently active span.
     */
    public static void log(String message) {
        activeSpan().ifPresent(s -> s.log(message));
    }

    public static <T> void log(String fld, String value) {
        activeSpan().ifPresent(s -> s.log(Collections.singletonMap(fld, value)));
    }

    public static void finishActiveSpan() {
        activeSpan().ifPresent(s -> s.finish());
    }

    private static Optional<Span> activeSpan() {
        Span span = getTracer().activeSpan();
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * This is package private on purpose. Users of the tracing module shouldn't be encumbered with tracers.
     * @return registered tracer
     */
    static Tracer getTracer() {
        return GlobalTracer.get();
    }
}
