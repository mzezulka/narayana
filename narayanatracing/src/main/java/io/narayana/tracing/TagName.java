package io.narayana.tracing;

/**
 * String constants to be used when creating span tags.
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public enum TagName {

    UID("uid"),
    XID("xid"),
    ASYNCHRONOUS("async"),
    XARES("xares"),
    STATUS("status"),
    TXINFO("info"),
    APPLICATION_ABORT("app_abrt"),
    COMMIT_OUTCOME("res_commit"),
    REPORT_HEURISTICS("report");

    private static final String TX_PREFIX = "transaction";
    private final String name;

    private TagName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return TX_PREFIX + "." + name;
    }
}
