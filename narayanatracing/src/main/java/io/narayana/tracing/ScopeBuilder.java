package io.narayana.tracing;

import java.util.Objects;

import io.opentracing.Scope;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tag;
import io.opentracing.tag.Tags;

import static io.narayana.tracing.TracingUtils.getTracer;

/**
 * Create a new span and activate it under new active scope.
 *
 *
 * Note: it is important to call the Scope.close() method (Scope implements
 * java.io.Closeable, so it is highly recommended to use the try-with-resources
 * idiom whenever possible)
 */
public final class ScopeBuilder {

    private SpanBuilder spanBldr;

    /**
     * @param name   name of the span which will be activated in the scope
     */
    public ScopeBuilder(SpanName name) {
        this.spanBldr = prepareSpan(name);
    }

    /**
     * Construct a span with a special Narayana-specific component tag "narayana"
     */
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

    public Scope start() {
        return getTracer().scopeManager().activate(spanBldr.withTag(Tags.COMPONENT, "narayana").start(), true);
    }

    public Scope startButDontFinish() {
        return getTracer().scopeManager().activate(spanBldr.withTag(Tags.COMPONENT, "narayana").start());
    }
}
