package com.arjuna.ats.internal.jta.opentracing;

import static com.arjuna.ats.internal.jta.opentracing.JtaTestUtils.*;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.*;
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

public class TracingTest {

    private static MockTracer testTracer = new MockTracer();
    private final TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    @BeforeClass
    public static void init() {
        // we've successfully registered our mock tracer (the flag tells us exactly that)
        assertThat(GlobalTracer.registerIfAbsent(testTracer)).isTrue();
    }

    @After
    public void teardown() {
        testTracer.reset();
    }

    @Test
    public void commitAndCheckRootSpan() throws Exception {
        jtaTwoPhaseCommit(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void commitAndCheckChildren() throws Exception {
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
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.GLOBAL_PREPARE,
                                                               SpanName.LOCAL_COMMIT,
                                                               SpanName.LOCAL_COMMIT,
                                                               SpanName.GLOBAL_COMMIT,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);
    }

    @Test
    public void userAbortAndCheckRootSpan() throws Exception {
        jtaUserRollback(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
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
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.GLOBAL_ABORT_USER,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);
    }

    @Test
    public void internalAbortAndCheckRootSpan() throws Exception {
        jtaPrepareResFail(tm);
        MockSpan root = getRootSpanFrom(testTracer.finishedSpans());
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isTrue();
    }

    @Test
    public void internalAbortAndCheckOperationNames() throws Exception {
        jtaPrepareResFail(tm);

        List<String> opNamesExpected = operationEnumsToStrings(SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.RESOURCE_ENLISTMENT,
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.GLOBAL_PREPARE,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.GLOBAL_ABORT,
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
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.LOCAL_PREPARE,
                                                               SpanName.GLOBAL_PREPARE,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.GLOBAL_ABORT,
                                                               SpanName.TX_ROOT,
                                                               SpanName.LOCAL_RECOVERY,
                                                               SpanName.LOCAL_RECOVERY);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        assertThat(spansToOperationStrings(spans)).isEqualTo(opNamesExpected);

        MockSpan root = spans.get(spans.size() - 3);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isTrue();

        MockSpan rec1 = spans.get(spans.size() - 1);
        MockSpan rec2 = spans.get(spans.size() - 2);
        assertThatSpans(rec1, rec2).haveParent(root);
        assertThat(rec1.logEntries()).isNotEmpty();
        assertThat(rec2.logEntries()).isNotEmpty();

        // this is how MockSpan logs events under the cover
        String rec1LogMsg = (String) rec1.logEntries().get(0).fields().get("event");
        String rec2LogMsg = (String) rec2.logEntries().get(0).fields().get("event");
        // TODO: why is the second pass performed before the first one?
        assertThat(rec1LogMsg).isEqualTo("second pass of the XAResource periodic recovery");
        assertThat(rec2LogMsg).isEqualTo("first pass of the XAResource periodic recovery");
    }
}