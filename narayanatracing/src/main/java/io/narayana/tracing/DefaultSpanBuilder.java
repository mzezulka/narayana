    package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.DUMMY_SPAN;
import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;
import static io.narayana.tracing.TracingUtils.activeSpan;
import static io.narayana.tracing.TracingUtils.getTracer;

import java.util.Objects;

import org.jboss.logging.Logger;

import io.narayana.tracing.names.SpanName;
import io.narayana.tracing.names.TagName;
import io.opentracing.Span;
import io.opentracing.SpanContext;
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
public class DefaultSpanBuilder {

    private SpanBuilder spanBldr;
    private SpanName name;
    private static final Logger LOG = Logger.getLogger(DefaultSpanBuilder.class);

    public DefaultSpanBuilder(SpanName name, Object... args) {
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
    public DefaultSpanBuilder tag(TagName name, Object val) {
        if(!TRACING_ACTIVATED) return this;
        Objects.requireNonNull(name);
        spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
        return this;
    }

    public <T> DefaultSpanBuilder tag(Tag<T> tag, T value) {
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
        Span parent = SpanRegistry.getPre2pc(txUid)
                .orElse(SpanRegistry.getRoot(txUid)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "No root span found for '%s' when building span with name '%s'.", txUid, name))));
        return spanBldr.asChildOf(parent).withTag(Tags.COMPONENT, "narayana").start();
    }

    /**
     * Build a regular span and attach it to the transaction with id {@code txUid}.
     *
     * It is expected that the trace is currently on an entirely different node and
     * tracing context needs to be extracted from a remote party and then this
     * context {@code spanContext} will be used as a "gluing" span for the spans in
     * the remote (coordinating) and spans on the node calling this method,
     * presumably a node on which a subordinate transaction is executed.
     *
     * If the span has already been registered, we only retrieve the existing span.
     *
     * @return {@code SpanHandle} with a started span
     */
    public Span buildSubordinateIfAbsent(String txUid, SpanContext spanContext) {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        return SpanRegistry.getRoot(txUid).orElseGet(() -> {
            Objects.requireNonNull(spanContext);
            Span span = spanBldr.asChildOf(spanContext).withTag(Tags.COMPONENT, "narayana").start();
            SpanRegistry.insertRoot(txUid, span);
            return span;
        });
    }

    /**
     * Build a span which has no explicit parent set. Useful for creating nested
     * traces. See the OpenTracing documentation on what the implicit reference is.
     *
     * @throws IllegalStateException there is currently no active span
     */
    public Span build() {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        if (!activeSpan().isPresent()) {
            throw new IllegalStateException(String
                    .format("The span '%s' could not be nested into enclosing span because there is none.", name));
        }
        return spanBldr.withTag(Tags.COMPONENT, "narayana").start();
    }
}
