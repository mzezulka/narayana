package io.narayana.tracing;

import static io.narayana.tracing.TracingUtils.DUMMY_SPAN;

import io.opentracing.Span;
import io.opentracing.noop.NoopScopeManager.NoopScope;

public class DummyScope implements NoopScope {

    @Override
    public void close() {
    }

    @Override
    public Span span() {
        return DUMMY_SPAN;
    }

}
