package io.narayana.tracing.names;

/**
 * String constants to be used as action names when creating spans.
 *
 * Naming conventions:
 * Spans starting with the "GT" prefix (global transaction) should be attached
 * to a TX_ROOT span. Spans starting with the "BRANCH" prefix suppose that there
 * is a an active span present in the ThreadLocalScope to which this span is
 * attached.
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
    GT_PREPARE("Global Prepare"),
    GT_COMMIT("Global Commit"),
    GT_ABORT("Global Abort"),
    GT_ABORT_USER("Global Abort - User Initiated"),
    ONE_PHASE_COMMIT("One phase commit"),
    BRANCH_PREPARE("Branch Prepare"),
    BRANCH_COMMIT("Branch Commit"),
    BRANCH_COMMIT_LAST_RESOURCE("Branch Commit - Last Resource Commit Optimization"),
    BRANCH_ROLLBACK("Branch Rollback"),
    BRANCH_RECOVERY("XAResource Recovery"),
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
