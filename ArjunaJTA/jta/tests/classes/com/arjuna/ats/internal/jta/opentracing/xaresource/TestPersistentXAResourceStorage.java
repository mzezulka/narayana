package com.arjuna.ats.internal.jta.opentracing.xaresource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.xa.Xid;

public class TestPersistentXAResourceStorage {

    private TestPersistentXAResourceStorage(File logStorageFile) throws IllegalAccessException {
        throw new IllegalAccessException("utility class, do not instantiate");
    }

    /**
     * Replaces content of the file {@link #getMockXAResourceTxnLogStore}
     * with the specified collection of xids.
     */
    static synchronized void writeToDisk(Collection<Xid> xidsToDisk) {
        Collection<Xid> writtableXidCollection = new ArrayList<>(xidsToDisk);
        File logStorageFile = getMockXAResourceTxnLogStore();

        try (FileOutputStream fos = new FileOutputStream(logStorageFile); ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(writtableXidCollection);
        } catch (IOException e) {
        }
    }

    @SuppressWarnings("unchecked")
    static synchronized Collection<Xid> recoverFromDisk() {
        Collection<Xid> recoveredXids = new HashSet<>();
        File logStorageFile = getMockXAResourceTxnLogStore();

        if (!logStorageFile.exists()) {
            return recoveredXids;
        }

        try (FileInputStream fis = new FileInputStream(logStorageFile); ObjectInputStream ois = new ObjectInputStream(fis)){
            Collection<Xid> xids = (Collection<Xid>) ois.readObject();
            recoveredXids.addAll(xids);
        } catch (Exception e) {
            return recoveredXids;
        }
        return recoveredXids;
    }

    private static File getMockXAResourceTxnLogStore() {
        String logStoragePath = "ObjectStore";
        File logDir = new File(logStoragePath);
        if(!logDir.exists()) {
            throw new IllegalStateException(String.format("Cannot get directory for the TestPersistentXAResource txn log storage named %s", logStoragePath));
        }

        File logFile = new File(logDir, TestPersistentXAResource.class.getSimpleName());
        return logFile;
    }
}
