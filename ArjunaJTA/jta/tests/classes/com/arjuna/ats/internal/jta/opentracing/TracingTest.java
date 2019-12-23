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

    private void jtaTwoPhaseCommit() throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.commit();
    }

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

    /**
     *  Basic commit with two records of instance com.hp.mwtests.ts.arjuna.resources.BasicRecord.
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
     *        6         8       7         "Branch Rollback"
     *        7         9       7         "Branch Rollback"
     *        8         7       2         "Global Abort"
     *        9         2       0         "Transaction"
     *  Please note that specific ids of spans may vary based on the mock implementation
     *  of the tracer, the important bit is the relations between spans.
     */
    @Test
    public void commitAndCheckRootSpan() throws Exception {
        jtaTwoPhaseCommit();
        assertThat(testTracer.finishedSpans().size()).isEqualTo(10);
        List<MockSpan> spans = testTracer.finishedSpans();

        MockSpan root = spans.get(9);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat(Arrays.asList(spans.get(8).parentId(),
                                 spans.get(5).parentId(),
                                 spans.get(2).parentId()))
                   .containsOnly(root.context().spanId());
    }

    @Test
    public void commitAndCheckOperationNames() throws Exception {
        jtaTwoPhaseCommit();
        List<String> opNames = testTracer.finishedSpans().stream().map(s -> s.operationName()).collect(Collectors.toList());
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
        assertThat(opNames).isEqualTo(opNamesExpected);
    }

    @Test
    public void abortAndCheckSpans() throws Exception {
        jtaRollback();
    }

    @Test
    public void abortAndCheckOperationNames() throws Exception {
        jtaRollback();
        List<String> opNames = testTracer.finishedSpans().stream().map(s -> s.operationName()).collect(Collectors.toList());
        List<String> opNamesExpected = operationEnumsToStrings(SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.LOCAL_RESOURCE_ENLISTMENT,
                                                               SpanName.GLOBAL_ENLISTMENTS,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.LOCAL_ROLLBACK,
                                                               SpanName.GLOBAL_ABORT_USER,
                                                               SpanName.TX_ROOT);
        assertThat(opNames).isEqualTo(opNamesExpected);
    }
}
