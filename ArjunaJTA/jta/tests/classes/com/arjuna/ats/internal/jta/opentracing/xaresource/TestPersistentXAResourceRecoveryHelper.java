package com.arjuna.ats.internal.jta.opentracing.xaresource;

import javax.transaction.xa.XAResource;

import com.arjuna.ats.jta.recovery.XAResourceRecoveryHelper;

public class TestPersistentXAResourceRecoveryHelper implements XAResourceRecoveryHelper {
    public static final TestPersistentXAResourceRecoveryHelper INSTANCE = new TestPersistentXAResourceRecoveryHelper();
    private static final XAResource mockXARecoveringInstance = new TestPersistentXAResource("default");

    private TestPersistentXAResourceRecoveryHelper() {
        if(INSTANCE != null) {
            throw new IllegalStateException("singleton instance can't be instantiated twice");
        }
    }

    @Override
    public boolean initialise(String p) throws Exception {
        // this is never called, probably...
        return true;
    }

    @Override
    public XAResource[] getXAResources() throws Exception {
        return new XAResource[] { mockXARecoveringInstance };
    }

}
