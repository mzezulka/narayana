package io.narayana.tracing;

import java.util.Collections;
import java.util.Optional;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tag;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 *
 */
public class TracingUtils {

    private TracingUtils() {
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

    public static void finishRootScope(String txUid) {
        ScopeBuilder.finish(txUid);
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
