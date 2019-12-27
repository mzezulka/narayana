package com.arjuna.ats.internal.jta.opentracing.xaresource;

import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.arjuna.recovery.RecoveryModule;
import com.arjuna.ats.internal.jta.recovery.arjunacore.XARecoveryModule;

public class TestPersistentXAResourceInitializer {

    private static final TestPersistentXAResourceInitializer INSTANCE = new TestPersistentXAResourceInitializer();
    private static boolean initialized = false;

    private TestPersistentXAResourceInitializer() {}

    public static TestPersistentXAResourceInitializer getInstance() {
        return INSTANCE;
    }
    /**
     * register the recovery module with the transaction manager.
     */
    public void initIfNecessary() {
        if(initialized) {
            return;
        }
        getRecoveryModule().addXAResourceRecoveryHelper(TestPersistentXAResourceRecoveryHelper.INSTANCE);
        TestPersistentXAResource.initPreparedXids(TestPersistentXAResourceStorage.recoverFromDisk());
        initialized = true;
    }

    /**
     * unregister the recovery module from the transaction manager.
     */
    public void cleaup() {
        getRecoveryModule().removeXAResourceRecoveryHelper(TestPersistentXAResourceRecoveryHelper.INSTANCE);
    }

    private XARecoveryModule getRecoveryModule() {
        for (RecoveryModule recoveryModule : RecoveryManager.manager().getModules()) {
            if (recoveryModule instanceof XARecoveryModule) {
                return (XARecoveryModule) recoveryModule;
            }
        }
        return null;
    }
}
