package io.narayana.tracing;

import io.opentracing.Span;

public final class SpanRegistry extends Registry<Span> {

    private static final SpanRegistry INSTANCE = new SpanRegistry();

    private SpanRegistry() {
    }

    public static SpanRegistry getInstance() {
        return INSTANCE;
    }
}
