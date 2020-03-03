package io.narayana.tracing;

import static io.narayana.tracing.TracingTestUtils.operationEnumsToStrings;
import static io.narayana.tracing.TracingTestUtils.spansToOperationStrings;
import static io.narayana.tracing.TracingUtils.finish;
import static io.narayana.tracing.TracingUtils.start;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import io.narayana.tracing.names.SpanName;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

/**
 * Unit tests for the opentracing Narayana facade.
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
        assertThat(spans.rootSpanCount()).isEqualTo(0);
        testTracer.reset();
    }

    @Test
    public void simpleTrace() {
        start(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.TX_ROOT);
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
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
        assertThat(spans.rootSpanCount()).isEqualTo(0);
        start(secondUid);
        finish(secondUid);
    }

    @Test(expected = Test.None.class /* no exception is expected to be thrown */)
    public void simpleTraceFinishTwoTransactionsInterleaved() {
        String firstUid = TEST_ROOT_UID + "1";
        String secondUid = TEST_ROOT_UID + "2";
        start(firstUid);
        start(secondUid);
        assertThat(spans.rootSpanCount()).isEqualTo(2);
        finish(firstUid);
        assertThat(spans.rootSpanCount()).isEqualTo(1);
        finish(secondUid);
    }

    @Test
    public void simpleTraceFinishTwoSameName() {
        try {
            start(TEST_ROOT_UID);
            start(TEST_ROOT_UID);
        } catch(IllegalArgumentException iae) {
            spans.reset();
            //ok
            return;
        }
        fail();
    }

    @Test
    public void nestedSpansSimple() {
        start(TEST_ROOT_UID);
        finish(TEST_ROOT_UID);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.TX_ROOT);
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
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
        assertThat(spansToOperationStrings(testTracer.finishedSpans())).isEqualTo(opNamesExpected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void spansWithExpectedRootMissing() {
        Span span = new NarayanaSpanBuilder(SpanName.GLOBAL_PREPARE).build(TEST_ROOT_UID);
    }

    /**
     * Some of the calls which demarcate the end of transaction processing
     * might be called more than once.
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
     * in the object store and recovered after AS restart).
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
    }
}
