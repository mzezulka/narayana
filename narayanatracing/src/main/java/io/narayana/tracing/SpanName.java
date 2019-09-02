package io.narayana.tracing;

/**
 * String constants and string formatters to be used as names when creating spans.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public enum SpanName {

    /*
     * The root span of the whole trace representing the transaction.
     */
    TX_ROOT("Transaction"),

    RESOURCE_ENLISTMENT("Resource enlistment"),

    GLOBAL_PRE_2PC("Pre 2PC"),
    GLOBAL_PREPARE("Global prepare (1st phase)"),
    GLOBAL_COMMIT("Commit (2nd phase)"),
    GLOBAL_ABORT("Abort (2nd phase)"),
    GLOBAL_ROLLBACK("Rollback (2nd phase)"),

    LOCAL_PREPARE("Branch prepare"),
    LOCAL_PREPARE_LAST_RESOURCE("Branch prepare - last resource commit optimization"),
    LOCAL_COMMIT("Branch commit"),
    LOCAL_COMMIT_LAST_RESOURCE("Branch commit - last resource commit optimization"),
    LOCAL_ROLLBACK("Branch rollback"),

    RECOVERY("RECOVERY");

    private final String name;

    private SpanName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
