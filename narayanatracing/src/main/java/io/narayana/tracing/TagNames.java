package io.narayana.tracing;

/**
 *
 * String constants to be used when creating span tags.
 * @author Miloslav Zezulka (mzezulka@redhat.com)
 */
public enum TagNames {
	
	UID("UID"),
	XID("XID"),
	REPORT_HEURISTICS("report heuristics");
	
	private final String name;
	
	private TagNames(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
