package io.narayana.tracing;

import io.opentracing.Span;
import io.opentracing.noop.NoopScopeManager.NoopScope;

public class DummyScope implements NoopScope {

    private static final Span DUMMY_SPAN = new DummySpan();

    @Override
    public void close() {
    }

    @Override
    public Span span() {
        return DUMMY_SPAN;
    }

}
