package io.narayana.tracing;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public class Tracing {

    private static final ConcurrentMap<String, Span> TX_UID_TO_SPAN = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, Span> TX_UID_TO_PRE2PC_SPAN = new ConcurrentHashMap<>();

    /**
     * Build a new root span handle which represents the whole transaction. Any
     * other span handles created in the Narayana code base should be attached to
     * this root scope using the "ordinary" SpanHandleBuilder.
     *
     * Usage: as the transaction will always begin and end at two different methods,
     * we need to manage (de)activation of the span manually, schematically like
     * this:
     *
     * <pre>
     * <code>public void transactionStart(Uid uid, ...) {
     *     new SpanHandleBuilder(SpanName.TX_BEGIN, uid)
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
     * Note: we're not using the Uid class as this would create a cyclic dependency
     * between narayanatracing arjuna modules. Strings (which should always
     * represent a Uid!) are used instead.
     */
    public static class RootSpanHandleBuilder {

        private SpanBuilder spanBldr;
        private SpanBuilder pre2PCspanBldr;

        public RootSpanHandleBuilder(Object... args) {
            spanBldr = prepareSpan(SpanName.TX_ROOT, args);
            pre2PCspanBldr = prepareSpan(SpanName.GLOBAL_PRE_2PC, args);
        }

        private static SpanBuilder prepareSpan(SpanName name, Object... args) {
            Objects.requireNonNull(name, "Name of the span cannot be null");
            return getTracer().buildSpan(String.format(name.toString(), args));
        }

        /**
         * Adds tag to the started span.
         */
        public RootSpanHandleBuilder tag(TagName name, Object val) {
            Objects.requireNonNull(name, "Name of the tag cannot be null");
            spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
            return this;
        }

        public <T> RootSpanHandleBuilder tag(Tag<T> tag, T value) {
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
        public SpanHandle build(String txUid) {
            Span rootSpan = spanBldr.withTag(Tags.COMPONENT, "narayana").ignoreActiveSpan().start();
            TX_UID_TO_SPAN.put(txUid, rootSpan);
            getTracer().scopeManager().activate(rootSpan);

            pre2PCspanBldr.asChildOf(rootSpan);
            Span pre2PCSpan = pre2PCspanBldr.withTag(Tags.COMPONENT, "narayana").start();
            TX_UID_TO_PRE2PC_SPAN.put(txUid, pre2PCSpan);
            return new SpanHandle(pre2PCSpan);
        }
    }

    /**
     * Responsibility of this class: create a new span handle. When building it,
     * make sure that the appropriate root span has already been created.
     *
     * Example of usage:
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
    public static class SpanHandleBuilder {

        private SpanBuilder spanBldr;
        private SpanName name;

        public SpanHandleBuilder(SpanName name, Object... args) {
            this.spanBldr = prepareSpan(name, args);
            this.name = name;
        }

        private static SpanBuilder prepareSpan(SpanName name, Object... args) {
            Objects.requireNonNull(name, "Name of the span cannot be null");
            return getTracer().buildSpan(String.format(name.toString(), args));
        }

        /**
         * Adds tag to the started span and simply calls the {@code toString} method on
         * {@code val}.
         */
        public SpanHandleBuilder tag(TagName name, Object val) {
            Objects.requireNonNull(name, "Name of the tag cannot be null");
            spanBldr = spanBldr.withTag(name.toString(), val == null ? "null" : val.toString());
            return this;
        }

        public <T> SpanHandleBuilder tag(Tag<T> tag, T value) {
            Objects.requireNonNull(tag, "Tag cannot be null.");
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
         * @return {@code SpanHandle with a started span}
         */
        public SpanHandle build(String txUid) {
            Span pre2PCSpan = TX_UID_TO_PRE2PC_SPAN.get(txUid);
            Span parent = pre2PCSpan == null ? TX_UID_TO_SPAN.get(txUid) : pre2PCSpan;

            if (parent == null) {
                // superfluous calls of abort can happen, ignore those
                if (name.isAbortAction()) {
                    return null;
                } else {
                    throw new IllegalStateException(String.format(
                            "There was an attempt to build span belonging to tx '%s' but no root span registered for it found! Span name: '%s', span map: '%s'",
                            txUid, name, TX_UID_TO_SPAN));
                }
            }
            spanBldr = spanBldr.asChildOf(parent);

            // ignore the Narayana reaper thread and do not activate any spans
            if (isRunningInReaperThread()) {
                return null;
            }
            return new SpanHandle(spanBldr.withTag(Tags.COMPONENT, "narayana").start());
        }

        /**
         * Build a span which has no explicit parent set. Useful for creating nested
         * traces. See the OpenTracing documentation on what the implicit reference is.
         *
         * @throws IllegalStateException there is currently no active span
         */
        public SpanHandle build() {
            if (!activeSpan().isPresent()) {
                throw new IllegalStateException(String
                        .format("The span '%s' could not be nested into enclosing span because there is none.", name));
            }
            return new SpanHandle(spanBldr.withTag(Tags.COMPONENT, "narayana").start());
        }
    }

    public static class SpanHandle {
        private final Span span;

        public SpanHandle(Span span) {
            this.span = span;
        }

        Span getSpan() {
            return span;
        }

        public void finish() {
            span.finish();
        }
    }

    private Tracing() {
    }

    private static boolean isRunningInReaperThread() {
        return Thread.currentThread().getName().toLowerCase().contains("reaper");
    }

    public static Scope activateSpan(SpanHandle spanHandle) {
        return getTracer().scopeManager().activate(spanHandle.getSpan());
    }

    /*
     * This method switches from the "pre-2PC" phase to the protocol phase.
     */
    public static void begin2PC(String txUid) {
        Span span = TX_UID_TO_PRE2PC_SPAN.remove(txUid);
        if (span != null)
            span.finish();
    }

    /**
     * Finishes root span representing the transaction with id {@code txUid}
     *
     * @param txUid
     */
    public static void finish(String txUid) {
        // We need to check for superfluous calls to this method
        Span span = TX_UID_TO_SPAN.remove(txUid);
        if (span != null)
            span.finish();
    }

    /**
     * This is different from setting the transaction status as failed. Using this
     * method, the span itself in terms of opentracing is marked as failed.
     *
     */
    public static void markTransactionFailed(String txUid) {
        Span span = TX_UID_TO_SPAN.get(txUid);
        if (span != null)
            span.setTag(Tags.ERROR, true);
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
        Span span = TX_UID_TO_SPAN.get(txUid);
        if (span != null)
            span.setTag(TagName.STATUS.toString(), status.toString().toLowerCase());
    }

    /**
     * Sets tag which for a span which is currently activated by the scope manager.
     * Useful when a user wishes to add tags whose existence / value is dependent on
     * the context (i.e. status of the transaction inside of the method call).
     */
    public static void addTag(TagName name, String val) {
        activeSpan().ifPresent(s -> s.setTag(name.toString(), val));
    }

    public static void addTag(TagName name, Object obj) {
        addTag(name, obj == null ? "null" : obj.toString());
    }

    public static <T> void addTag(Tag<T> tag, T obj) {
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

    private static Optional<Span> activeSpan() {
        Span span = getTracer().activeSpan();
        return span == null ? Optional.empty() : Optional.of(span);
    }

    /**
     * This is package private on purpose. Users of the tracing module shouldn't be
     * encumbered with tracers.
     *
     * @return registered tracer or any default tracer provided by the opentracing implementation
     */
    static Tracer getTracer() {
        return GlobalTracer.get();
    }
}
