package io.narayana.tracing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.xa.XAResource;

public class XAResourceToStringCache {

    private static final Map<XAResource, String> CACHE = new ConcurrentHashMap<>();


    public static String get(XAResource xaRes) {
        return CACHE.computeIfAbsent(xaRes, x -> x.toString());
    }

    public static void purge(XAResource xaRes) {
        CACHE.remove(xaRes);
    }
}
