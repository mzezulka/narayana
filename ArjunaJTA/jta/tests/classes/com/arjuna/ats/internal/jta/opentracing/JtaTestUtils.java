package com.arjuna.ats.internal.jta.opentracing;
import java.lang.reflect.Field;
import java.sql.Timestamp;

import javax.transaction.RollbackException;
import javax.transaction.TransactionManager;

import com.arjuna.ats.arjuna.common.recoveryPropertyManager;
import com.arjuna.ats.arjuna.recovery.RecoveryManager;
import com.arjuna.ats.internal.jta.opentracing.xaresource.TestPersistentXAResource;
import com.arjuna.ats.internal.jta.opentracing.xaresource.TestPersistentXAResource.FaultType;
import com.arjuna.ats.internal.jta.opentracing.xaresource.TestPersistentXAResourceInitializer;
import com.arjuna.ats.internal.jta.recovery.arjunacore.RecoveryXids;
import com.arjuna.ats.internal.jta.recovery.arjunacore.XARecoveryModule;
import com.hp.mwtests.ts.jta.common.FailureXAResource;
import com.hp.mwtests.ts.jta.common.XACreator;
import com.hp.mwtests.ts.jta.recovery.XARROne;
import com.hp.mwtests.ts.jta.recovery.XARRTwo;

public class JtaTestUtils {


    /**
     *  Basic commit intermediated by JTA which makes use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        10       3         "Enlistment"
     *        1        11       3         "Enlistment"
     *        2         3       2         "XAResource Enlistments"
     *        3         5       4         "Branch Prepare"
     *        4         6       4         "Branch Prepare"
     *        5         4       2         "Global Prepare"
     *        6         8       7         "Branch Commit"
     *        7         9       7         "Branch Commit"
     *        8         7       2         "Global Commit"
     *        9         2       0         "Transaction"
     *  Please note that specific ids of spans may vary based on the mock implementation
     *  of the tracer, the important bit is the relations between spans. This holds true
     *  for any other test.
     */
    static void jtaTwoPhaseCommit(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.commit();
    }

    /**
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        53      52          "Enlistment"
     *        1        54      52          "Enlistment"
     *        2        52      51          "XAResource Enlistments"
     *        3        56      55          "Branch Prepare"
     *        4        57      55          "Branch Prepare"
     *        5        55      51          "Global Prepare"
     *        6        59      58          "Branch Rollback"
     *        7        60      58          "Branch Rollback"
     *        8        58      51          "Global Abort"
     *        9        51       0          "Transaction"
     *       10        61      51          "XAResource Recovery"
     *       11        62      51          "XAResource Recovery"
     *
     */
    static void jtaWithRecovery(TransactionManager tm) throws Exception {
          recoveryPropertyManager.getRecoveryEnvironmentBean().setRecoveryBackoffPeriod(1);
          XARecoveryModule xaRecoveryModule = setupTestXaRecoveryModule(new XARecoveryModule());
          RecoveryManager manager = RecoveryManager.manager(RecoveryManager.DIRECT_MANAGEMENT);
          manager.addModule(xaRecoveryModule);

          TestPersistentXAResourceInitializer initializer = TestPersistentXAResourceInitializer.getInstance();
          initializer.initIfNecessary();

          tm.begin();
          Timestamp ts = new Timestamp(System.currentTimeMillis());
          tm.getTransaction().enlistResource(new TestPersistentXAResource("demo" + ts.getTime(), FaultType.FIRST_ROLLBACK_RMFAIL));
          tm.getTransaction().enlistResource(new TestPersistentXAResource("demo" + ts.getTime() + 1, FaultType.PREPARE_FAIL));
          try {
              tm.commit();
          } catch(RollbackException re) {
              // expected
              manager.scan();
          } finally {
              initializer.cleaup();
          }
    }

    private static XARecoveryModule setupTestXaRecoveryModule(XARecoveryModule xaRecoveryModule) throws Exception {
        Field safetyIntervalMillis = RecoveryXids.class.getDeclaredField("safetyIntervalMillis");
        safetyIntervalMillis.setAccessible(true);
        safetyIntervalMillis.set(null, 0);
        xaRecoveryModule.addXAResourceRecoveryHelper(new XARROne());
        xaRecoveryModule.addXAResourceRecoveryHelper(new XARRTwo());
        xaRecoveryModule.addXAResourceOrphanFilter(new com.arjuna.ats.internal.jta.recovery.arjunacore.JTATransactionLogXAResourceOrphanFilter());
        xaRecoveryModule.addXAResourceOrphanFilter(new com.arjuna.ats.internal.jta.recovery.arjunacore.JTANodeNameXAResourceOrphanFilter());
        return xaRecoveryModule;
    }

    /**
     *  User initiated JTA abort, making use of two XAResources.
     *  Spans are reported in the following order:
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        6        5         "Enlistment"
     *        1        7        5         "Enlistment"
     *        2        5        4         "XAResource Enlistments"
     *        3        9        8         "Branch Rollback"
     *        4       10        8         "Branch Rollback"
     *        5        8        4         "Global Abort - User Initiated"
     *        6        4        0         "Transaction"
     */
    static void jtaUserRollback(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.rollback();
    }

    /**
     * Make use of existing failing XAResources and force the JTA transaction to fail in the prepare phase.
     *
     *
     *    span index   id    parentid        op name
     *    in 'spans'
     *    ================================================
     *        0        10       3         "Enlistment"
     *        1        11       3         "Enlistment"
     *        2         3       2         "XAResource Enlistments"
     *        3         5       4         "Branch Prepare"
     *        4         6       4         "Branch Prepare"
     *        5         4       2         "Global Prepare"
     *        6         8       7         "Branch Rollback"
     *        7         9       7         "Branch Rollback"
     *        8         7       2         "Global Abort"
     *        9         2       0         "Transaction"
     */
    static void jtaPrepareResFail(TransactionManager tm) throws Exception {
        String xaResource = "com.hp.mwtests.ts.jta.common.DummyCreator";
        XACreator creator = (XACreator) Thread.currentThread().getContextClassLoader().loadClass(xaResource).newInstance();
        String connectionString = null;

        tm.begin();
        tm.getTransaction().enlistResource(creator.create(connectionString, true));
        tm.getTransaction().enlistResource(new FailureXAResource(FailureXAResource.FailLocation.prepare));
        try {
            tm.commit();
        } catch (RollbackException re) {
            Class<?> cl = javax.transaction.xa.XAException.class;
            if(re.getSuppressed().length < 1) {
                throw new RuntimeException("Expected suppressed exceptions (especially XAException) but got none.");
            }
            for(Throwable t : re.getSuppressed()) {
                if(t.getClass().equals(cl)) {
                    // ok, we've found the suppressed exception corresponding to XAResource prepare fail
                    return;
                }
            }
            throw new RuntimeException("Did not find expected suppressed exception of type " + cl, re);
        }
    }
}
