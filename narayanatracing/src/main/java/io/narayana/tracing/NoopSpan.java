package io.narayana.tracing;

import java.util.Map;

import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.tag.Tag;

public class NoopSpan implements Span {

    @Override
    public SpanContext context() {
        return null;
    }

    @Override
    public Span setTag(String key, String value) {
        return this;
    }

    @Override
    public Span setTag(String key, boolean value) {
        return this;
    }

    @Override
    public Span setTag(String key, Number value) {
        return this;
    }

    @Override
    public <T> Span setTag(Tag<T> tag, T value) {
        return this;
    }

    @Override
    public Span log(Map<String, ?> fields) {
        return this;
    }

    @Override
    public Span log(long timestampMicroseconds, Map<String, ?> fields) {
        return this;
    }

    @Override
    public Span log(String event) {
        return this;
    }

    @Override
    public Span log(long timestampMicroseconds, String event) {
        return this;
    }

    @Override
    public Span setBaggageItem(String key, String value) {
        return this;
    }

    @Override
    public String getBaggageItem(String key) {
        return null;
    }

    @Override
    public Span setOperationName(String operationName) {
        return this;
    }

    @Override
    public void finish() {
    }

    @Override
    public void finish(long finishMicros) {
    }

}
