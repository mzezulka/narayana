package com.arjuna.ats.internal.jta.transaction.arjunacore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.xa.XAResource;

public class XAResourceToStringCache {

    private static final Map<XAResource, String> CACHE = new ConcurrentHashMap<>();

    public static String get(XAResource xaRes) {
        return CACHE.computeIfAbsent(xaRes, x -> x.toString());
    }

    public static synchronized void purge(XAResource xaRes) {
        int numBef = CACHE.size();
        if(CACHE.remove(xaRes) != null) assert(numBef - 1 == CACHE.size());
    }
}
