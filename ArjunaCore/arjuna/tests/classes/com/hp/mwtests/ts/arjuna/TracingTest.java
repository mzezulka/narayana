package com.hp.mwtests.ts.arjuna;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.arjuna.ats.arjuna.AtomicAction;
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
        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.commit();

        assertThat(testTracer.finishedSpans().size()).isEqualTo(6);
        List<MockSpan> spans = testTracer.finishedSpans();
        // spans are stored in the array in postorder (which responds to
        // the ordering determined by the time each span is reported)
        // in the following way:
        //
        // span index   id    parentid        op name
        // in 'spans'
        // ================================================
        //     0         5       4         "Branch Prepare"
        //     1         6       4         "Branch Prepare"
        //     2         4       3         "Global Prepare"
        //     3         8       7         "Branch Commit"
        //     4         9       7         "Branch Commit"
        //     5         7       3         "Global Commit"
        //
        // please note that specific ids of spans may vary based on the implementation
        // of the mock opentracing, the important bit is the relations between spans
        MockSpan s0 = spans.get(0);
        MockSpan s1 = spans.get(1);
        MockSpan s2 = spans.get(2);
        MockSpan s3 = spans.get(3);
        MockSpan s4 = spans.get(4);
        MockSpan s5 = spans.get(5);
    }
}
