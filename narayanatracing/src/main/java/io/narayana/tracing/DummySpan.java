package io.narayana.tracing;

import java.util.Map;

import io.opentracing.SpanContext;
import io.opentracing.noop.NoopSpan;
import io.opentracing.tag.Tag;

public class DummySpan implements NoopSpan {

    @Override
    public SpanContext context() {
        return null;
    }

    @Override
    public void finish() {
    }

    @Override
    public void finish(long finishMicros) {
    }

    @Override
    public NoopSpan setTag(String key, String value) {
        return this;
    }

    @Override
    public NoopSpan setTag(String key, boolean value) {
        return this;
    }

    @Override
    public NoopSpan setTag(String key, Number value) {
        return this;
    }

    @Override
    public <T> NoopSpan setTag(Tag<T> tag, T value) {
        return this;
    }

    @Override
    public NoopSpan log(Map<String, ?> fields) {
        return this;
    }

    @Override
    public NoopSpan log(long timestampMicroseconds, Map<String, ?> fields) {
        return this;
    }

    @Override
    public NoopSpan log(String event) {
        return this;
    }

    @Override
    public NoopSpan log(long timestampMicroseconds, String event) {
        return this;
    }

    @Override
    public NoopSpan setBaggageItem(String key, String value) {
        return this;
    }

    @Override
    public String getBaggageItem(String key) {
        return null;
    }

    @Override
    public NoopSpan setOperationName(String operationName) {
        return this;
    }

    @Override
    public String toString() {
        return DummySpan.class.getSimpleName();
    }
}
