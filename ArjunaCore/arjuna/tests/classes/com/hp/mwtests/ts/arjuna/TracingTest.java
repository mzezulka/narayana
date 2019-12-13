package com.hp.mwtests.ts.arjuna;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.arjuna.ats.arjuna.AtomicAction;
import com.hp.mwtests.ts.arjuna.resources.BasicRecord;

import io.opentracing.mock.MockTracer;
import io.opentracing.util.GlobalTracer;

public class TracingTest {

//    @Mock
//    private Tracer mockTracer;
//
//    @Mock
//    private Tracer.SpanBuilder mockSpanBuilder;
//
//    @Mock
//    private ScopeManager mockScopeManager;
//
//    @Mock
//    private Scope mockScope;
//
//    @Mock
//    private Span mockSpan;
//
//    @Mock
//    private SpanContext mockSpanContext;

    @Before
    public void before() {
//      MockitoAnnotations.initMocks(this);
//      when(mockTracer.buildSpan(anyString())).thenReturn(mockSpanBuilder);
//      when(mockSpanBuilder.asChildOf(any(SpanContext.class))).thenReturn(mockSpanBuilder);
//      when(mockSpanBuilder.start()).thenReturn(mockSpan);
//      when(mockScopeManager.activate(mockSpan)).thenReturn(mockScope);
//      when(mockTracer.scopeManager()).thenReturn(mockScopeManager);
//      when(mockScopeManager.activeSpan()).thenReturn(mockSpan);
//      when(mockSpanBuilder.withTag(anyString(), anyString())).thenReturn(mockSpanBuilder);
//      when(mockSpan.context()).thenReturn(mockSpanContext);
    }

    @Test
    public void begin() {
        MockTracer mt = new MockTracer();
        GlobalTracer.registerIfAbsent(mt);
        AtomicAction a = new AtomicAction();
        a.begin();
        a.commit();
        assertThat(mt.finishedSpans().size()).isEqualTo(2);
    }

    @Test
    public void commit() {
        BasicRecord record1 = new BasicRecord();
        BasicRecord record2 = new BasicRecord();

        AtomicAction a = new AtomicAction();
        a.begin();
        a.add(record1);
        a.add(record2);
        a.commit();
    }

}
