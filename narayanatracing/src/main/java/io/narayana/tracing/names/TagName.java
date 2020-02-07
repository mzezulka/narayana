package io.narayana.tracing.names;

/**
 * String constants to be used when creating span tags.
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public enum TagName {

    UID("uid"),
    XID("xid"),
    ASYNCHRONOUS("async"),
    XARES("xares"),
    XARES_CLASS("xares_class"),
    STATUS("status"),
    TXINFO("info"),
    APPLICATION_ABORT("app_abrt"),
    RECOVERY_MODULE_TYPE("recovery_type"),
    COMMIT_OUTCOME("res_commit"),
    TRANSACTION_TIMEOUT("txn_timeout"),
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
