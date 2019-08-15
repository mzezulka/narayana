package io.narayana.tracing;

/**
 *
 * String constants to be used when creating span tags.
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public enum TagName {

    UID("UID"),
    XID("XID"),
    ASYNCHRONOUS("asynchronous"),
    XARES("XAResource"),
    TXINFO("transaction"),
    APPLICATION_ABORT("application initiated abort"),
    REPORT_HEURISTICS("report heuristics");

    private final String name;

    private TagName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
