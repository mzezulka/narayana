package io.narayana.tracing;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.api.ListAssert;

import io.narayana.tracing.names.SpanName;
import io.opentracing.mock.MockSpan;
import io.opentracing.tag.Tags;

public class TracingTestUtils {
    public static List<String> operationEnumsToStrings(SpanName... ops) {
        return Arrays.asList(ops).stream().map(s -> s.toString()).collect(Collectors.toList());
    }

    /**
     * Takes a list of reported spans and maps them to their names under which they
     * were reported. Order of the span names stays the same with regards to order of
     * the input span list.
     */
    public static List<String> spansToOperationStrings(List<MockSpan> spans) {
        return spans.stream().map(s -> s.operationName()).collect(Collectors.toList());
    }

    public static Set<String> spansToComponentNames(List<MockSpan> spans) {
        return spans.stream().map(s -> (String) s.tags().get(Tags.COMPONENT.getKey())).collect(Collectors.toSet());
    }

    /*
     * Retrieve the root span which must always sit at the very end of the collection (because
     * spans are reported in a postorder fashion.
     */
    public static MockSpan getRootSpanFrom(List<MockSpan> spans) {
        return spans.get(spans.size()-1);
    }

    // AssertJ-like assert for easier manipulation with opentracing Spans
    public static class SpanListAssert extends ListAssert<Long> {
        public SpanListAssert(List<? extends Long> actual) {
            super(actual);
        }

        public ListAssert<Long> haveParent(MockSpan span) {
            return super.containsOnly(span.context().spanId());
        }
    }

    public static SpanListAssert assertThatSpans(MockSpan... spans) {
        return new SpanListAssert(Arrays.asList(spans).stream().map(s -> s.parentId()).collect(Collectors.toList()));
    }
}
