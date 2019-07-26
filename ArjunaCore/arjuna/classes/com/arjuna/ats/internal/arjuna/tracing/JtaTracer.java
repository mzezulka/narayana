package com.arjuna.ats.internal.arjuna.tracing;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;

/**
 * Class enabling to utilise tracing in the code by providing an instantiated
 * and setup Tracer class.
 * 
 * Implemented using the singleton pattern.
 */
public class JtaTracer {

	private static final JtaTracer INSTANCE = new JtaTracer();
	private final Tracer tracer;

	/**
	 * registerIfAbsent is not avaiable from the Jaeger version 0.35.5
	 */
	@SuppressWarnings("deprecation")
	private JtaTracer() {
		SamplerConfiguration samplerConfig = new SamplerConfiguration().withType("const").withParam(1);
		SenderConfiguration senderConfig = new SenderConfiguration().withAgentHost("localhost").withAgentPort(5776);
		ReporterConfiguration reporterConfig = new ReporterConfiguration().withLogSpans(true).withFlushInterval(1000)
				.withMaxQueueSize(10000).withSender(senderConfig);
		Configuration config = new Configuration("JTA").withSampler(samplerConfig).withReporter(reporterConfig);
		tracer = config.getTracer();
		GlobalTracer.register(tracer);
	}
	
	public static JtaTracer getInstance() {
		return INSTANCE;
	}
	
	public Tracer getTracer() {
		return tracer;
	}
}
