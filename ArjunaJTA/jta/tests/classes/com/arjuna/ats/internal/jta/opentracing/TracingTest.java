package com.arjuna.ats.internal.jta.opentracing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.TransactionManager;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hp.mwtests.ts.jta.common.XACreator;

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

    /**
     *  Basic commit intermediated by JTA which makes use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        10       3         "Enlistment"
     *        1        11       3         "Enlistment"
     *        2         3       2         "XAResource Enlistments"
     *        3         5       4         "Branch Prepare"
     *        4         6       4         "Branch Prepare"
     *        5         4       2         "Global Prepare"
     *        6         8       7         "Branch Commit"
     *        7         9       7         "Branch Commit"
     *        8         7       2         "Global Commit"
     *        9         2       0         "Transaction"
     *  Please note that specific ids of spans may vary based on the mock implementation
     *  of the tracer, the important bit is the relations between spans. This holds true
     *  for any other test.
     */
    private void jtaTwoPhaseCommit() throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.commit();
    }

    /**
     *  User initiated JTA abort, making use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        6        5         "Enlistment"
     *        1        7        5         "Enlistment"
     *        2        5        4         "XAResource Enlistments"
     *        3        9        8         "Branch Rollback"
     *        4       10        8         "Branch Rollback"
     *        5        8        4         "Global Abort - User Initiated"
     *        6        4        0         "Transaction"
     */
    private void jtaRollback() throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.rollback();
    }

    private List<String> operationEnumsToStrings(SpanName... ops) {
        return Arrays.asList(ops).stream().map(s -> s.toString()).collect(Collectors.toList());
    }

    /*
     * Retrieve the root span which must always sit at the very end of the collection (because
     * spans are reported in a postorder fashion.
     */
    private MockSpan getRootSpanFrom(List<MockSpan> spans) {
        return spans.get(spans.size()-1);
    }

    @Test
    public void commitAndCheckRootSpan() throws Exception {
        jtaTwoPhaseCommit();
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
    }

    @Test
    public void commitAndCheckChildren() throws Exception {
        jtaTwoPhaseCommit();
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        //                                   tx-root
        //                              /       |         \
        //       "XAResource Enlistments" "Global Prepare" "Global Abort - User Initiated"
        assertThat(Arrays.asList(spans.get(8).parentId(),
                                 spans.get(5).parentId(),
                                 spans.get(2).parentId()))
                   .containsOnly(root.context().spanId());
    }

    @Test
    public void commitAndCheckOperationNames() throws Exception {
        jtaTwoPhaseCommit();
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
        List<String> opNames = spans.stream().map(s -> s.operationName()).collect(Collectors.toList());
        assertThat(opNames).isEqualTo(opNamesExpected);
    }

    @Test
    public void abortAndCheckRootSpan() throws Exception {
        jtaRollback();
        List<MockSpan> spans = testTracer.finishedSpans();
        MockSpan root = getRootSpanFrom(spans);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        // this is *user-initiated* abort
        assertThat((boolean) root.tags().get(Tags.ERROR.getKey())).isFalse();
        }

    @Test
    public void abortAndCheckChildren() throws Exception {
        jtaRollback();
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
        jtaRollback();
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
