package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.getTracer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tag;
import io.opentracing.tag.Tags;

/**
 * Create a new span and activate it under new active scope.
 * <pre>
 * <code>try (Scope s = new ScopeBuilder(SpanName.TX_BEGIN).start()) {
 *     // this is where 's' is active
 * } finally {
 *     TracingUtils.finishActiveSpan();
 * }
 * </code>
 * </pre>
 */
public final class ScopeBuilder {

    private SpanBuilder spanBldr;
    private static final Map<String, Span> TX_UID_TO_SPAN_MAP = new HashMap<>();

    /**
     * @param name name of the span which will be activated in the scope
     */
    public ScopeBuilder(SpanName name) {
        this.spanBldr = prepareSpan(name);
    }

    private static SpanBuilder prepareSpan(SpanName name) {
        Objects.requireNonNull(name, "Name of the span cannot be null");
        return getTracer().buildSpan(name.toString());
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
     * Finishes root span representing the transaction with id {@code txUid}
     * @param txUid
     */
    public static void finish(String txUid) {
        TX_UID_TO_SPAN_MAP.get(txUid).finish();
    }

    /**
     * @throws IllegalArgumentException {@code txUid} is null or a span with this ID already exists
     * @param txUid UID of the new transaction
     * @return
     */
    public Scope startRootSpan(String txUid) {
        Span span = spanBldr.withTag(Tags.COMPONENT, "narayana").start();
        TX_UID_TO_SPAN_MAP.put(txUid, span);
        return getTracer().scopeManager().activate(span);
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
}
