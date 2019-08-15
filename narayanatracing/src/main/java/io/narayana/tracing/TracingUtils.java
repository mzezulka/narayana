package io.narayana.tracing;

import java.util.Collections;

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
    public static void addCurrentSpanTag(TagName name, String val) {
        getTracer().activeSpan().setTag(name.toString(), val);
    }

    public static void addCurrentSpanTag(TagName name, Object obj) {
        addCurrentSpanTag(name, obj == null ? "null" : obj.toString());
    }

    public static <T> void addCurrentSpanTag(Tag<T> tag, T obj) {
        getTracer().activeSpan().setTag(tag, obj);
    }

    /**
     * Log a message for the currently active span.
     */
    public static void log(String message) {
        getTracer().activeSpan().log(message);
    }

    public static <T> void log(String fld, String value) {
        getTracer().activeSpan().log(Collections.singletonMap(fld, value));
    }

    public static void finishActiveSpan() {
        Span span = getTracer().activeSpan();
        if(span != null) span.finish();
    }

    /**
     * This is package private on purpose. Users of the tracing module shouldn't be encumbered with tracers.
     * @return registered tracer
     */
    static Tracer getTracer() {
        return GlobalTracer.get();
    }
}
