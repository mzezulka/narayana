package com.hp.mwtests.ts.arjuna;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.arjuna.ats.arjuna.AtomicAction;
import com.hp.mwtests.ts.arjuna.resources.BasicRecord;

import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

public class TracingTest {

    @Before
    public void before() {
    }

    @Test
    public void commit() {
        MockTracer mt = new MockTracer();
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();
        GlobalTracer.registerIfAbsent(mt);
        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.commit();
        assertThat(mt.finishedSpans().size()).isEqualTo(2);
    }
}
