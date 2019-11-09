package io.narayana.tracing.names;

/**
 * Copied from com.arjuna.ats.arjuna.coordinator.ActionStatus
 * (and transformed into a proper enum).
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public enum TransactionStatus {
    RUNNING,
    PREPARING,
    ABORTING,
    ABORT_ONLY,
    ABORTED,
    PREPARED,
    COMMITTING,
    COMMITTED,
    CREATED,
    INVALID,
    CLEANUP,
    H_ROLLBACK,
    H_COMMIT,
    H_MIXED,
    H_HAZARD,
    DISABLED,
    NO_ACTION
}
