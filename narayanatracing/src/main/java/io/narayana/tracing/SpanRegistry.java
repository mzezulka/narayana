package io.narayana.tracing;

import io.opentracing.Span;

/**
 * Storage for spans which need to be finished manually and are not a responsibility
 * of a regular opentracing scope manager.
 *
 * This class is thread safe.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public class SpanRegistry extends Registry<Span> {

    private static final SpanRegistry INSTANCE = new SpanRegistry();

    private SpanRegistry() {
    }

    public static SpanRegistry getInstance() {
        return INSTANCE;
    }
}
