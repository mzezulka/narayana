package io.narayana.tracing.names;

/**
 * String constants and string formatters to be used as names when creating spans.
 *
 * Naming conventions:
 * Spans starting with the "GLOBAL" prefix should be attached to a TX_ROOT span.
 * Spans starting with the "LOCAL" prefix suppose that there is a GLOBAL span created
 *     and is currently activated by the scope manager. In other words, they always
 *     belong to a superior, more abstract action which is not TX_ROOT.
 *
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public enum SpanName {

    /*
     * The root span of the whole trace representing the transaction.
     */
    TX_ROOT("Transaction"),
    SUBORD_ROOT("Subordinate transaction"),

    GLOBAL_PREPARE("Global Prepare"),
    GLOBAL_COMMIT("Global Commit"),
    GLOBAL_ABORT("Global Abort"),
    GLOBAL_ABORT_USER("Global Abort - User Initiated"),

    ONE_PHASE_COMMIT("One phase commit"),

    LOCAL_PREPARE("Branch Prepare"),
    LOCAL_COMMIT("Branch Commit"),
    LOCAL_COMMIT_LAST_RESOURCE("Branch Commit - Last Resource Commit Optimization"),
    LOCAL_ROLLBACK("Branch Rollback"),
    LOCAL_RECOVERY("XAResource Recovery"),
    RESOURCE_ENLISTMENT("XAResource Enlistment");

    private final String name;

    private SpanName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
