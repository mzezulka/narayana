package com.hp.mwtests.ts.arjuna;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.arjuna.common.Uid;
import com.hp.mwtests.ts.arjuna.resources.BasicRecord;

import io.opentracing.mock.MockSpan;
import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

public class TracingTest {

    private MockTracer testTracer;

    @Before
    public void init() {
        testTracer = new MockTracer();
        GlobalTracer.registerIfAbsent(testTracer);
    }

    @After
    public void teardown() {
        testTracer.reset();
    }

    /**
     * Basic commit with two records.
     */
    @Test
    public void commit() {
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();
        Uid uid = new Uid("commit-test");
        AtomicAction a = new AtomicAction(uid);
        a.begin();
        a.add(record1);
        a.add(record2);
        a.commit();

        //assertThat(testTracer.finishedSpans().size()).isEqualTo(6);
        List<MockSpan> spans = testTracer.finishedSpans();
        // spans are stored in the array in postorder (which responds to
        // the ordering determined by the time each span is reported)
        // in the following way:
        //
        // span index   id    parentid        op name
        // in 'spans'
        // ================================================
        //     0         3       2         "XAResource Enlistments"
        //     1         5       4         "Branch Prepare"
        //     2         6       4         "Branch Prepare"
        //     3         4       2         "Global Prepare"
        //     4         8       7         "Branch Rollback"
        //     5         9       7         "Branch Rollback"
        //     6         7       2         "Global Abort"
        //     7         2       0         "Transaction"
        // please note that specific ids of spans may vary based on the implementation
        // of the mock opentracing, the important bit is the relations between spans
        MockSpan root = spans.get(7);
        // parent id 0 == no parent exists == the root of a trace
        assertThat(root.parentId()).isEqualTo(0);
        assertThat(root.operationName()).isEqualTo("Transaction");
        assertThat(Arrays.asList(spans.get(6).parentId(),
                                 spans.get(3).parentId(),
                                 spans.get(0).parentId()))
                   .containsOnly(root.context().spanId());
//        StringBuilder sb = new StringBuilder();
//        for(MockSpan s : testTracer.finishedSpans()) {
//            sb.append(s).append('\n');
//        }
//        throw new RuntimeException(sb.toString());
    }

    //@Test
    public void abort() {
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();
        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.preventCommit();
        a.commit();
    }
}
