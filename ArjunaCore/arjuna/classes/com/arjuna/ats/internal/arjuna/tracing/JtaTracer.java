package com.arjuna.ats.internal.arjuna.tracing;

import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 * 
 * NOTE: This class will most probably be deleted out.
 */
public class JtaTracer {

	private static final JtaTracer INSTANCE = new JtaTracer();	

	/**
	 * registerIfAbsent is not available from the Jaeger version 0.35.5
	 */
	private JtaTracer() {
	}
	
	public static JtaTracer getInstance() {
		return INSTANCE;
	}
	
	public Tracer getTracer() {
		return GlobalTracer.get();
	}
}
