    package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;
import static io.narayana.tracing.TracingUtils.getTracer;
import static io.narayana.tracing.names.StringConstants.NARAYANA_COMPONENT_NAME;

import java.util.Objects;

import io.narayana.tracing.logging.TracingLogger;
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
 * <code>SpanHandle handle = new SpanHandleBuilder(SpanName.XYZ)
 *    .tag(TagName.UID, get_uid())
 *    .build(get_uid().toString());
 * try (Scope s = Tracing.activateHandle(handle)) {
 *     // this is where 's' is active
 * } finally {
 *     handle.finish();
 * }
 * </code>
 * </pre>
 */
public class NarayanaSpanBuilder {

    private SpanBuilder spanBldr;
    private SpanName name;
    private static final Span DUMMY_SPAN = new DummySpan();
    private SpanRegistry spans = SpanRegistry.getInstance();

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
     * Build a regular span and attach it to the transaction with id {@code txUid}.
     *
     * Note: if the "real" part of a transaction processing hasn't started yet and
     * the transaction manager needs to do some preparations (i.e. XAResource
     * enlistment), the trace is in a pseudo "pre-2PC" state where every span is
     * hooked to a SpanName.GLOBAL_PRE_2PC span (which is always a child of a root
     * span).
     *
     * @param txUid id of a transaction which already has a root span
     * @throws IllegalStateException no appropriate span for {@code txUid} exists
     * @return {@code SpanHandle} with a started span
     */
    public Span build(String txUid) {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        Span parent = spans.get(txUid).orElseGet(() -> {TracingLogger.i18NLogger.warnNoRootSpan(txUid, name); return getTracer().activeSpan();});
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
