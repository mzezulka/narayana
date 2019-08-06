package io.narayana.tracing;

import java.util.Objects;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 * 
 */
public class TracingUtils {

	private TracingUtils() {
	}

	/**
	 * Sets tag which is currently activated by the scope manager.
	 */
	public static void addCurrentSpanTag(TagNames name, String val) {
		getTracer().activeSpan().setTag(name.toString(), val);
	}

	/**
	 * This is package private on purpose. Users of the tracing module shouldn't be encumbered with tracers.
	 * Nevertheless, this method is used outside of this class.
	 * @return
	 */
	static Tracer getTracer() {
		return GlobalTracer.get();
	}
}
