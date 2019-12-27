package com.arjuna.ats.internal.jta.opentracing.xaresource;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.jboss.tm.XAResourceWrapper;

public class TestPersistentXAResource implements XAResource, XAResourceWrapper, Serializable {

    // using Set for two Xids would not be part of the collection
    private static final Collection<Xid> preparedXids = ConcurrentHashMap.newKeySet();
    private String name;
    private FaultType fault = FaultType.NONE;
    private static boolean noRollbackYet = true;
    private int transactionTimeout;

    public enum FaultType {
        TIMEOUT, PREPARE_FAIL, NONE, FIRST_ROLLBACK_RMFAIL, FIRST_COMMIT_RMFAIL
    }

    public TestPersistentXAResource() {
        this("default xares");
    }

    public TestPersistentXAResource(String name) {
        this(name, FaultType.NONE);
    }

    public TestPersistentXAResource(String name, FaultType fault) {
        this.name = name;
        this.fault = fault;
    }

    @Override
    public void commit(Xid xid, boolean b) throws XAException {
        if (fault == FaultType.TIMEOUT)
            throw new XAException(XAException.XA_RBTIMEOUT);
        removeLog(xid);
    }

    @Override
    public void end(Xid xid, int i) throws XAException {
    }

    @Override
    public void forget(Xid xid) throws XAException {
        removeLog(xid);
    }

    @Override
    public int getTransactionTimeout() throws XAException {
        return transactionTimeout;
    }

    @Override
    public boolean isSameRM(XAResource xaResource) throws XAException {
        return equals(xaResource);
    }

    @Override
    public int prepare(Xid xid) throws XAException {
        if (fault == FaultType.PREPARE_FAIL) {
            throw new XAException(XAException.XAER_RMFAIL);
        }
        preparedXids.add(xid);
        TestPersistentXAResourceStorage.writeToDisk(preparedXids);
        return XAResource.XA_OK;
    }

    @Override
    public Xid[] recover(int i) throws XAException {
        return preparedXids.toArray(new Xid[preparedXids.size()]);
    }

    @Override
    public void rollback(Xid xid) throws XAException {
        if (fault == FaultType.FIRST_ROLLBACK_RMFAIL && noRollbackYet) {
            noRollbackYet = false;
            throw new XAException(XAException.XAER_RMFAIL);
        }
        removeLog(xid);
    }

    @Override
    public boolean setTransactionTimeout(int seconds) throws XAException {
        this.transactionTimeout = seconds;
        return true;
    }

    @Override
    public void start(Xid xid, int i) throws XAException {
    }

    /**
     * Loading 'prepared' xids from the persistent file storage.
     * Expected to be used just at the start of the application.
     */
    static synchronized void initPreparedXids(Collection<Xid> xidsToBeDefinedAsPrepared) {
        preparedXids.addAll(xidsToBeDefinedAsPrepared);
    }

    private void removeLog(Xid xid) {
        preparedXids.remove(xid);
        TestPersistentXAResourceStorage.writeToDisk(preparedXids);
    }

    @Override
    public XAResource getResource() {
        throw new UnsupportedOperationException("getResource() method from "
                + XAResourceWrapper.class.getName() + " is not implemented yet");
    }

    @Override
    public String getProductName() {
        return TestPersistentXAResource.class.getSimpleName();
    }

    @Override
    public String getProductVersion() {
        return "0.1.Mock";
    }

    @Override
    public String getJndiName() {
        String jndi = "java:/" + TestPersistentXAResource.class.getSimpleName();
        return jndi;
    }

    @Override
    public String toString() {
        return "XAResourceWrapperImpl@[xaResource=" + super.toString() + " pad=false overrideRmValue=null productName="
                + name + " productVersion=1.0 jndiName=java:jboss/" + name + "]";
    }

}
