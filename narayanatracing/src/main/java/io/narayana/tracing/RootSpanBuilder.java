package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.DUMMY_SPAN;
import static io.narayana.tracing.TracingUtils.TRACING_ACTIVATED;
import static io.narayana.tracing.TracingUtils.getTracer;

import java.util.Objects;

import io.narayana.tracing.names.SpanName;
import io.narayana.tracing.names.TagName;
import io.opentracing.Span;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.tag.Tag;
import io.opentracing.tag.Tags;

/**
 * Build a new root span handle which represents the whole transaction. Any
 * other span handles created in the Narayana code base should be attached to
 * this root scope using the "ordinary" SpanBuilder.
 *
 * Usage: as the transaction will always begin and end at two different methods,
 * we need to manage (de)activation of the span manually, schematically like
 * this:
 *
 * <pre>
 * <code>public void transactionStart(Uid uid, ...) {
 *     new RootSpanBuilder(uid)
 *            ...
 *           .build(uid);
 *     ...
 *}
 *
 *public void transactionEnd(Uid uid, ...) {
 *     Tracing.finish(uid);
 *     ...
 * </code>}
 * </pre>
 *
 * Note: we're not using the Uid class as a key as this would create a cyclic dependency
 * between narayanatracing arjuna modules. Strings (which should always
 * represent a Uid!) are used instead.
 */
public class RootSpanBuilder {

    private SpanBuilder spanBldr;
    private SpanBuilder pre2PCspanBldr;

    public RootSpanBuilder(Object... args) {
        if(!TRACING_ACTIVATED) return;
        spanBldr = prepareSpan(SpanName.TX_ROOT, args).withTag(Tags.ERROR, false);
        pre2PCspanBldr = prepareSpan(SpanName.GLOBAL_ENLISTMENTS, args);
    }

    private static SpanBuilder prepareSpan(SpanName name, Object... args) {
        Objects.requireNonNull(name, "Name of the span cannot be null");
        return getTracer().buildSpan(String.format(name.toString(), args));
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

    public <T> RootSpanBuilder tag(Tag<T> tag, T value) {
        if(!TRACING_ACTIVATED) return this;
        Objects.requireNonNull(tag, "Tag cannot be null.");
        spanBldr = spanBldr.withTag(tag, value);
        return this;
    }

    /**
     * Build the root span and propagate it as a handle. Any possible active
     * (=parent) spans are ignored as this is the root of a new transaction trace.
     *
     * @throws IllegalArgumentException {@code txUid} is null or a span with this ID
     *                                  already exists
     * @param txUid UID of the new transaction
     * @return
     */
    public Span build(String txUid) {
        if(!TRACING_ACTIVATED) return DUMMY_SPAN;
        Span rootSpan = spanBldr.withTag(Tags.COMPONENT, "narayana").ignoreActiveSpan().start();
        SpanRegistry.insertRoot(txUid, rootSpan);
        getTracer().scopeManager().activate(rootSpan);

        Span pre2PCSpan = pre2PCspanBldr.asChildOf(rootSpan).withTag(Tags.COMPONENT, "narayana").start();
        SpanRegistry.insertPre2pc(txUid, pre2PCSpan);
        getTracer().scopeManager().activate(pre2PCSpan);

        return pre2PCSpan;
    }
}
