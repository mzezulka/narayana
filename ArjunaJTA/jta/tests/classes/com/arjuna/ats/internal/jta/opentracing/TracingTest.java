package com.arjuna.ats.internal.jta.opentracing;

import static com.arjuna.ats.internal.jta.opentracing.JtaTestUtils.*;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.*;
import static io.narayana.tracing.names.StringConstants.NARAYANA_COMPONENT_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.TransactionManager;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import io.narayana.tracing.names.SpanName;
import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;


/**
 * Integration tests of OpenTracing integration into Narayana.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public class TracingTest {

    private static MockTracer testTracer = new MockTracer();
    private final TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    @BeforeClass
    public static void init() {
        // we've successfully registered our mock tracer
        // (the return value of registerIfAbsent tells us exactly that)
        assertThat(GlobalTracer.registerIfAbsent(testTracer)).isTrue();
    }

    @After
    public void teardown() {
        // check that all spans ever reported in a test were reported under the "narayana" module
        assertThat(spansToComponentNames(testTracer.finishedSpans())).containsOnly(NARAYANA_COMPONENT_NAME);
        testTracer.reset();
    }

    @Test
    public void successfullCommitAndCheckRootSpan() throws Exception {
        jtaTwoPhaseCommit(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // check that the transaction trace is not marked as failed
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void successfullCommitAndCheckRootSpans() throws Exception {
        jtaTwoPhaseCommit(tm);
        MockSpan root1 = getRootSpanFrom(testTracer.finishedSpans());
        assertThat(testTracer.activeSpan()).isNull();
        jtaTwoPhaseCommit(tm);
        MockSpan root2 = getRootSpanFrom(testTracer.finishedSpans());
        // two different transactions are reported under different traces
        assertThat(root2.parentId()).isNotEqualTo(root1.context().spanId());
    }

    @Test
    public void successfullcommitAndCheckChildren() throws Exception {
        jtaTwoPhaseCommit(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        MockSpan globalPrepare = spans.get(4);
        MockSpan globalCommit = spans.get(7);
        assertThatSpans(globalPrepare, globalCommit).haveParent(root);

        MockSpan enlistment1 = spans.get(0);
        MockSpan enlistment2 = spans.get(1);
        assertThatSpans(enlistment1, enlistment2).haveParent(root);

        MockSpan prepare1 = spans.get(2);
        MockSpan prepare2 = spans.get(3);
        assertThatSpans(prepare1, prepare2).haveParent(globalPrepare);

        MockSpan commit1 = spans.get(5);
        MockSpan commit2 = spans.get(6);
        assertThatSpans(commit1, commit2).haveParent(globalCommit);
    }

    @Test
    public void commitAndCheckOperationNames() throws Exception {
        jtaTwoPhaseCommit(tm);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.GT_PREPARE,
                                                               SpanName.BRANCH_COMMIT,
                                                               SpanName.BRANCH_COMMIT,
                                                               SpanName.GT_COMMIT,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);
    }

    @Test
    public void userAbortAndCheckRootSpan() throws Exception {
        jtaUserRollback(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // this is *user-initiated* abort, we don't want to mark this trace as failed
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void userAbortAndCheckChildren() throws Exception {
        jtaUserRollback(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        MockSpan globalAbort = spans.get(4);
        assertThatSpans(globalAbort).haveParent(root);

        MockSpan enlist1 = spans.get(0);
        MockSpan enlist2 = spans.get(1);
        assertThatSpans(enlist1, enlist2).haveParent(root);

        MockSpan rollback1 = spans.get(2);
        MockSpan rollback2 = spans.get(3);
        assertThatSpans(rollback1, rollback2).haveParent(globalAbort);
    }

    @Test
    public void userAbortAndCheckOperationNames() throws Exception {
        jtaUserRollback(tm);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.GT_ABORT_USER,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);
    }

    @Test
    public void internalAbortAndCheckRootSpan() throws Exception {
        jtaPrepareResFail(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // in the case of internal Narayana errors, we want to mark this trace as failed
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isTrue();
    }

    @Test
    public void internalAbortAndCheckOperationNames() throws Exception {
        jtaPrepareResFail(tm);

        List<String> opNamesExpected = operationEnumsToStrings(SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.GT_PREPARE,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.GT_ABORT,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);
    }

    @Test
    public void internalAbortAndCheckChildren() throws Exception {
        jtaPrepareResFail(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        MockSpan globalPrepare = spans.get(4);
        MockSpan globalAbort = spans.get(7);
        assertThatSpans(globalPrepare, globalAbort).haveParent(root);

        MockSpan enlistment1 = spans.get(0);
        MockSpan enlistment2 = spans.get(1);
        assertThatSpans(enlistment1, enlistment2).haveParent(root);

        MockSpan prepare1 = spans.get(2);
        MockSpan prepare2 = spans.get(3);
        assertThatSpans(prepare1, prepare2).haveParent(globalPrepare);

        MockSpan abort1 = spans.get(5);
        MockSpan abort2 = spans.get(6);
        assertThatSpans(abort1, abort2).haveParent(globalAbort);
    }

    @Test
    public void recovery() throws Exception {
        jtaWithRecovery(tm);

        List<String> opNamesExpected = operationEnumsToStrings(SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.BRANCH_PREPARE,
                                                               SpanName.GT_PREPARE,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.BRANCH_ROLLBACK,
                                                               SpanName.GT_ABORT,
                                                               SpanName.TX_ROOT,
                                                               SpanName.BRANCH_RECOVERY,
                                                               SpanName.BRANCH_RECOVERY);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);

        MockSpan root = spans.get(spans.size() - 3);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isTrue();

        MockSpan rec1 = spans.get(spans.size() - 1);
        MockSpan rec2 = spans.get(spans.size() - 2);
        assertThatSpans(rec1, rec2).doNotHaveParent(root);
        assertThat(rec1.logEntries()).isNotEmpty();
        assertThat(rec2.logEntries()).isNotEmpty();

        // this is how MockSpan logs events under the cover
        String rec1LogMsg = (String) rec1.logEntries().get(0).fields().get("event");
        String rec2LogMsg = (String) rec2.logEntries().get(0).fields().get("event");
        // let us explicitly check what the two reported spans are since we don't want
        // the last one especially to be the root span (recovery spans are reported outside
        // of the txn processing span)
        assertThat(rec1LogMsg).isEqualTo("second pass of the XAResource periodic recovery");
        assertThat(rec2LogMsg).isEqualTo("first pass of the XAResource periodic recovery");
    }
}