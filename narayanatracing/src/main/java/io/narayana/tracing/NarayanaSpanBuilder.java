    package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;
import static io.narayana.tracing.TracingUtils.getTracer;
import static io.narayana.tracing.names.StringConstants.NARAYANA_COMPONENT_NAME;

import java.util.Objects;

import io.narayana.tracing.names.SpanName;
import io.narayana.tracing.names.StringConstants;
import io.narayana.tracing.names.TagName;
import io.opentracing.Span;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tag;
import io.opentracing.tag.Tags;

/**
 * Create a new span handle. When building it, make sure that the appropriate
 * root span has already been created.
 *
 * Example of usage:
 *
 * <pre>
 * <code>Span span = new NarayanaSpanBuilder(SpanName.XYZ)
 *    .tag(TagName.UID, get_uid())
 *    .build(get_uid().toString());
 * try (Scope s = Tracing.activateSpan(span)) {
 *     // this is where 's' is active
 * } finally {
 *     span.finish();
 * }
 * </code>
 * </pre>
 */
public class NarayanaSpanBuilder {

    private SpanBuilder spanBldr;
    private SpanName name;
    private static final Span DUMMY_SPAN = new DummySpan();

    public NarayanaSpanBuilder(SpanName name, Object... args) {
        if(!TRACING_ACTIVATED) return;
        this.spanBldr = prepareSpan(name, args);
        this.name = name;
    }

    private static SpanBuilder prepareSpan(SpanName name, Object... args) {
        Objects.requireNonNull(name);
        return getTracer().buildSpan(String.format(name.toString(), args));
    }

    /**
     * Adds tag to the started span and simply calls the {@code toString} method on
     * {@code val}.
     */
    public NarayanaSpanBuilder tag(TagName name, Object val) {
        if(!TRACING_ACTIVATED) return this;
        Objects.requireNonNull(name);
        String stringForm = val == null ? "null" : val.toString();
        spanBldr = spanBldr.withTag(name.toString(), stringForm);
        return this;
    }

    public <T> NarayanaSpanBuilder tag(Tag<T> tag, T value) {
        if(!TRACING_ACTIVATED) return this;
        Objects.requireNonNull(tag);
        spanBldr = spanBldr.withTag(tag, value);
        return this;
    }

    /**
     * Build a regular span and attach it to the parent {@code parent}.
     *
     * @param parent parent span
     * @return {@code SpanHandle} with a started span
     */
    public Span build(Span parent) {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        return spanBldr.asChildOf(parent).withTag(Tags.COMPONENT, StringConstants.NARAYANA_COMPONENT_NAME).start();
    }

    /**
     * Build a span which does not declare its parent explicitly.
     * Useful for creating nested traces.
     *
     * Use with extreme caution as call to this method does not ensure
     * that the span will be associated to any transaction trace.
     *
     * @throws IllegalStateException there is currently no active span
     */
    public Span build() {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        return spanBldr.withTag(Tags.COMPONENT, NARAYANA_COMPONENT_NAME).start();
    }
}
