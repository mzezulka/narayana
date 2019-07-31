package com.arjuna.ats.internal.arjuna.tracing;

import java.util.Objects;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 * 
 * NOTE: This class will most probably be deleted out.
 */
public class TracerUtils {

	private TracerUtils() {
	}
	
	/**
	 * Construct a span with a special Narayana-specific tag NARAYANA for convenience
	 * (the tag might prove useful when filtering various collected traces)
	 */
	public static Span getSpanWithName(String name) {
		Objects.requireNonNull(name, "Name of the span cannot be null");
		SpanBuilder bldr = getTracer().buildSpan(name);
		bldr.asChildOf(getTracer().activeSpan());
	    return bldr.start().setTag("NARAYANA", true);
	}
	
	/**
	 * Retrieve registered Tracer using the GlobalTracer utility class.
	 */
	public static Tracer getTracer() {
		return GlobalTracer.get();
	}
}
