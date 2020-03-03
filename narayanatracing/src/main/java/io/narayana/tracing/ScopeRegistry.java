package io.narayana.tracing;

public class ScopeRegistry {
    private static final ScopeRegistry INSTANCE = new ScopeRegistry();

    private ScopeRegistry() {
    }

    public static ScopeRegistry getInstance() {
        return INSTANCE;
    }

}
