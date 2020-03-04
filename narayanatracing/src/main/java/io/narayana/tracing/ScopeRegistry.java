package io.narayana.tracing;

import io.opentracing.Scope;

public final class ScopeRegistry extends Registry<Scope> {
    private static final ScopeRegistry INSTANCE = new ScopeRegistry();

    private ScopeRegistry() {
    }

    public static ScopeRegistry getInstance() {
        return INSTANCE;
    }

}
