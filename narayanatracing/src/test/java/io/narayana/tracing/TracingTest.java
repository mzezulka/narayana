package io.narayana.tracing;

import static io.narayana.tracing.TracingTestUtils.*;
import static io.narayana.tracing.TracingUtils.finish;
import static io.narayana.tracing.TracingUtils.start;
import static io.narayana.tracing.names.StringConstants.NARAYANA_COMPONENT_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import io.narayana.tracing.names.SpanName;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

/**
 * Unit tests for the opentracing Narayana facade. We do not incorporate Arjuna, only
 * simulate transaction processing manually from the perspective of the OpenTracing API.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public class TracingTest {

    private static MockTracer testTracer = new MockTracer();
    private static final String TEST_ROOT_UID = "TEST-ROOT";
    private static SpanRegistry spans = SpanRegistry.getInstance();

    @BeforeClass
    public static void init() {
        // we've successfully registered our mock tracer (the flag tells us exactly that)
        assertThat(GlobalTracer.registerIfAbsent(testTracer)).isTrue();
    }

    @After
    public void teardown() {
        // this makes sure that any spans reported during a test will be deleted
        testTracer.reset();
    }

    @Test
    public void simpleTrace() {
        start(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.TX_ROOT);
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
        assertThat(spansToComponentNames(testTracer.finishedSpans())).containsOnly(NARAYANA_COMPONENT_NAME);
    }

    @Test(expected = Test.None.class /* no exception is expected to be thrown */)
    public void simpleTraceFinishTwice() {
        start(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
    }

    @Test(expected = Test.None.class /* no exception is expected to be thrown */)
    public void simpleTraceFinishTwoTransactionsInSeries() {
        String firstUid = TEST_ROOT_UID + "1";
        String secondUid = TEST_ROOT_UID + "2";
        start(firstUid);
        finish(firstUid);
        assertThat(spans.elementsCount()).isEqualTo(0);
        start(secondUid);
        finish(secondUid);
    }

    @Test(expected = Test.None.class /* no exception is expected to be thrown */)
    public void simpleTraceFinishTwoTransactionsInterleaved() {
        String firstUid = TEST_ROOT_UID + "1";
        String secondUid = TEST_ROOT_UID + "2";
        start(firstUid);
        start(secondUid);
        assertThat(spans.elementsCount()).isEqualTo(2);
        finish(firstUid);
        assertThat(spans.elementsCount()).isEqualTo(1);
        finish(secondUid);
    }

    @Test
    public void simpleTraceFinishTwoSameName() {
        try {
            start(TEST_ROOT_UID);
            start(TEST_ROOT_UID);
        } catch(IllegalArgumentException iae) {
            assertThat(spans.elementsCount()).isEqualTo(1);
            finish(TEST_ROOT_UID);
            return;
        }
        fail();
    }

    @Test
    public void nestedSpans() {
        start(TEST_ROOT_UID);
        Span span = new NarayanaSpanBuilder(SpanName.GLOBAL_PREPARE).build(TEST_ROOT_UID);
        try (Scope _s = TracingUtils.activateSpan(span)) {
            //no-op
        } finally {
            span.finish();
            finish(TEST_ROOT_UID);
        }
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.GLOBAL_PREPARE, SpanName.TX_ROOT);
        MockSpan globalPrepareSpan = testTracer.finishedSpans().get(0);
        MockSpan rootSpan = testTracer.finishedSpans().get(1);
        assertThat(globalPrepareSpan.parentId()).isEqualTo(rootSpan.context().spanId());
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
        assertThat(spansToComponentNames(testTracer.finishedSpans())).containsOnly(NARAYANA_COMPONENT_NAME);
    }

    @Test(expected = Test.None.class)
    public void spansWithExpectedRootMissing() {
        // we only report a log warning and proclaim the active span as the parent of the span (if any active span currently exists)
        Span span = new NarayanaSpanBuilder(SpanName.GLOBAL_PREPARE).build(TEST_ROOT_UID);
    }

    /**
     * Some of the calls which demarcate the end of transaction processing
     * might be called more than once (e.g. reaper thread). Let us simulate such scenario.
     */
    @Test(expected = Test.None.class)
    public void finishTxnWithoutStartingIt() {
        start(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
    }

    /*
     * This test case makes sure that narayanatracing does not throw IAE
     * when XARecoveryModule processes transactions which are
     * unknown to tracing runtime (e.g. txns which are stored persistently
     * in the object store and recovered after Narayana restart).
     *
     * Recovery is the most prominent example but the most important
     * aspect of this test is that for the NarayanaSpanBuilder,
     * we do not pass in any txn id.
     */
    @Test(expected = Test.None.class)
    public void spansWithExpectedRootMissingNoFail() {
        Span span = new NarayanaSpanBuilder(SpanName.LOCAL_RECOVERY).build();
        try (Scope _s = TracingUtils.activateSpan(span)) {
            //no-op
        } finally {
            span.finish();
        }
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.LOCAL_RECOVERY);
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
        assertThat(spansToComponentNames(testTracer.finishedSpans())).containsOnly(NARAYANA_COMPONENT_NAME);
    }
}
