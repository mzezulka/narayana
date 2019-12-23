package com.hp.mwtests.ts.arjuna;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.arjuna.ats.arjuna.AtomicAction;
import com.hp.mwtests.ts.arjuna.resources.BasicRecord;

import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

public class TracingTest {

    private static MockTracer testTracer = new MockTracer();

    @BeforeClass
    public static void init() {
        // we've successfully registered our mock tracer (the flag tells us exactly that)
        assertThat(GlobalTracer.registerIfAbsent(testTracer)).isTrue();
    }

    @After
    public void teardown() {
        testTracer.reset();
    }

    private void arjunaTwoPhaseCommit() {
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();
        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.commit();
    }

    private void arjunaAbort() {
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();
        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.preventCommit();
        a.commit();
    }

    @Test
    public void commitAndCheckOperationNames() {
        arjunaTwoPhaseCommit();
        List<String> opNames = testTracer.finishedSpans().stream().map(s -> s.operationName()).collect(Collectors.toList());
        List<String> opNamesExpected = Arrays.asList("XAResource Enlistments",
                                                     "Branch Prepare",
                                                     "Branch Prepare",
                                                     "Global Prepare",
                                                     "Branch Commit",
                                                     "Branch Commit",
                                                     "Global Commit",
                                                     "Transaction");
        assertThat(opNames).isEqualTo(opNamesExpected);
    }

    /**
     *  Basic commit with two records of instance com.hp.mwtests.ts.arjuna.resources.BasicRecord.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0         3       2         "XAResource Enlistments"
     *        1         5       4         "Branch Prepare"
     *        2         6       4         "Branch Prepare"
     *        3         4       2         "Global Prepare"
     *        4         8       7         "Branch Rollback"
     *        5         9       7         "Branch Rollback"
     *        6         7       2         "Global Abort"
     *        7         2       0         "Transaction"
     *  Please note that specific ids of spans may vary based on the mock implementation
     *  of the tracer, the important bit is the relations between spans.
     */
    @Test
    public void commitAndCheckRootSpan() {
        arjunaTwoPhaseCommit();
        assertThat(testTracer.finishedSpans().size()).isEqualTo(8);
        List<MockSpan> spans = testTracer.finishedSpans();

        MockSpan root = spans.get(7);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat(Arrays.asList(spans.get(6).parentId(),
                                 spans.get(3).parentId(),
                                 spans.get(0).parentId()))
                   .containsOnly(root.context().spanId());
    }

    @Test
    public void abortAndCheckSpans() {
        arjunaAbort();
    }
}
