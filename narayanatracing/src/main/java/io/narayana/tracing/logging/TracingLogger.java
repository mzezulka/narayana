package io.narayana.tracing.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.jboss.logging.Logger;

public class TracingLogger implements InvocationHandler
{
    private static final String PKG_NAME = "io.narayana.tracing";
    public static final Logger logger = Logger.getLogger(PKG_NAME);
    public static final TracingI18NLogger i18NLogger = (TracingI18NLogger) Proxy.newProxyInstance(
            TracingI18NLogger.class.getClassLoader(),
            new Class[] { TracingI18NLogger.class },
            new TracingLogger(Logger.getMessageLogger(TracingI18NLogger.class, PKG_NAME)));

    private TracingI18NLogger jtaI18NLoggerImpl;
    private static boolean recoveryProblems;

    private TracingLogger() {
    }

    private TracingLogger(TracingI18NLogger logger) {
        jtaI18NLoggerImpl = logger;
    }

    public static boolean isRecoveryProblems() {
        return recoveryProblems;
    }

    public static void setRecoveryProblems(boolean recoveryProblems) {
        TracingLogger.recoveryProblems = recoveryProblems;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("warn_recovery")) {
                recoveryProblems = true;
            }

            return method.invoke(jtaI18NLoggerImpl, args);
        } catch (InvocationTargetException e) {
            throw e.getCause() != null ? e.getCause() : e;
        }
    }
}
