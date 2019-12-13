package com.hp.mwtests.ts.arjuna;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.opentracing.Scope;
import io.opentracing.ScopeManager;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;

public class TracingTest {

    @Mock
    private Tracer mockTracer;

    @Mock
    private Tracer.SpanBuilder mockSpanBuilder;

    @Mock
    private ScopeManager mockScopeManager;

    @Mock
    private Scope mockScope;

    @Mock
    private Span mockSpan;

    @Mock
    private SpanContext mockSpanContext;

    @Before
    public void before() {
      MockitoAnnotations.initMocks(this);
      when(mockTracer.buildSpan(anyString())).thenReturn(mockSpanBuilder);
      when(mockSpanBuilder.asChildOf(any(SpanContext.class))).thenReturn(mockSpanBuilder);
      when(mockSpanBuilder.start()).thenReturn(mockSpan);
      when(mockScopeManager.activate(mockSpan)).thenReturn(mockScope);
      when(mockTracer.scopeManager()).thenReturn(mockScopeManager);
      when(mockScopeManager.activeSpan()).thenReturn(mockSpan);
      when(mockSpanBuilder.withTag(anyString(), anyString())).thenReturn(mockSpanBuilder);
      when(mockSpan.context()).thenReturn(mockSpanContext);
    }

    @Test
    public void commit() {


    }

}
