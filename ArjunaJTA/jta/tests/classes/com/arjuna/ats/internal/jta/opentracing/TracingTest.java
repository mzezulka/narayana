package com.arjuna.ats.internal.jta.opentracing;

import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.assertThatSpans;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.getRootSpanFrom;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.jtaRollback;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.jtaTwoPhaseCommit;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.operationEnumsToStrings;
import static com.arjuna.ats.internal.jta.opentracing.TracingTestUtils.spansToOperationStrings;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void commitAndCheckChildren() throws Exception {
        jtaTwoPhaseCommit(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        MockSpan globalEnlist = spans.get(2);
        MockSpan globalPrepare = spans.get(5);
        MockSpan globalCommit = spans.get(8);
        assertThatSpans(globalEnlist, globalPrepare, globalCommit).haveParent(root);

        MockSpan enlistment1 = spans.get(0);
        MockSpan enlistment2 = spans.get(1);
        assertThatSpans(enlistment1, enlistment2).haveParent(globalEnlist);

        MockSpan prepare1 = spans.get(3);
        MockSpan prepare2 = spans.get(4);
        assertThatSpans(prepare1, prepare2).haveParent(globalPrepare);

        MockSpan commit1 = spans.get(6);
        MockSpan commit2 = spans.get(7);
        assertThatSpans(commit1, commit2).haveParent(globalCommit);
    }

    @Test
    public void commitAndCheckOperationNames() throws Exception {
        jtaTwoPhaseCommit(tm);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.GLOBAL_ENLISTMENTS,
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
    public void abortAndCheckRootSpan() throws Exception {
        jtaRollback(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        // this is *user-initiated* abort
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void abortAndCheckChildren() throws Exception {
        jtaRollback(tm);
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        //                               tx-root
        //                              /      \
        //       "XAResource Enlistments"      "Global Commit"
        assertThat(Arrays.asList(spans.get(2).parentId(), spans.get(5).parentId()))
                  .containsOnly(root.context().spanId());
    }

    @Test
    public void abortAndCheckOperationNames() throws Exception {
        jtaRollback(tm);
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.GLOBAL_ENLISTMENTS,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.GLOBAL_ABORT_USER,
                                                               SpanName.TX_ROOT);
        List<MockSpan> spans = testTracer.finishedSpans();
        assertThat(spans.size()).isEqualTo(opNamesExpected.size());
        List<String> opNames = spans.stream().map(s -> s.operationName()).collect(Collectors.toList());
        assertThat(opNames).isEqualTo(opNamesExpected);
    }
}
