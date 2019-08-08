package io.narayana.tracing;

/**
 * String constants and formatting templates to be used as names when creating spans.
 * 
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 *
 */
public enum SpanName {

	/**
	 * The root span of the whole trace representing the transaction.
	 * 
	 * template params:
	 * 1: transaction UID
	 */
	GLOBAL_TRANSACTION("Transaction %s"),
	/**
	 * template params:
	 * 1: resource description
	 */
	RESOURCE_ENLISTMENT("Enlistment of resource"),
	GLOBAL_PREPARE("Global prepare (1st phase)"),
	GLOBAL_COMMIT("Commit (2nd phase)"),
	GLOBAL_ABORT("Abort (2nd phase)"),
	GLOBAL_ROLLBACK("Rollback (2nd phase)"),
	/**
	 * template params:
	 * 1: branch ID
	 */
	LOCAL_PREPARE("Branch '%s' prepare"),
	/**
	 * template params:
	 * 1: branch ID
	 */
	LOCAL_COMMIT("Branch '%s' commit"),
	/**
	 * template params:
	 * 1: branch ID
	 */
	LOCAL_ROLLBACK("Branch '%s' rollback"),
	// TODO
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
