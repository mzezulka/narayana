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
 * <code>try (Scope _ignored = new ScopeBuilder(SpanName.TX_BEGIN).start()) {
 *     // scope of the _ignored
 * }
 * </code>
 * </pre>
 */
public final class ScopeBuilder {

    private SpanBuilder spanBldr;
    private static final Map<String, Span> TX_UID_TO_SPAN_MAP = new HashMap<>();

    /**
     * @param name   name of the span which will be activated in the scope
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

//    public Scope start() {
//        return getTracer().scopeManager().activate(spanBldr.withTag(Tags.COMPONENT, "narayana").start(), true);
//    }

    public Scope start(String txUid) {
        spanBldr.asChildOf(TX_UID_TO_SPAN_MAP.get(txUid));
        Span span = spanBldr.withTag(Tags.COMPONENT, "narayana").start();
        return getTracer().scopeManager().activate(span, true);
    }

    public static void finish(String txUid) {
        TX_UID_TO_SPAN_MAP.get(txUid).finish();
    }

    public Scope startWithoutSpanFinish(String txUid) {
        Span span = spanBldr.withTag(Tags.COMPONENT, "narayana").start();
        TX_UID_TO_SPAN_MAP.put(txUid, span);
        return getTracer().scopeManager().activate(span);
    }
}
