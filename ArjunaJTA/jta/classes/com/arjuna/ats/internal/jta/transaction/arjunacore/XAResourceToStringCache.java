package com.arjuna.ats.internal.jta.transaction.arjunacore;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.xa.XAResource;

public class XAResourceToStringCache {

    private static final Map<XAResource, String> CACHE = new ConcurrentHashMap<>();


    public static String get(XAResource xaRes) {
        Objects.requireNonNull(xaRes);
        return CACHE.computeIfAbsent(xaRes, x -> x.toString());
    }

    public static void purge(XAResource xaRes) {
        if (CACHE.remove(xaRes) == null) throw new IllegalArgumentException();
    }
}
