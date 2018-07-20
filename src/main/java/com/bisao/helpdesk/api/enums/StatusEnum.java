package com.bisao.helpdesk.api.enums;

public enum StatusEnum {
	NEW, ASSIGNED, RESOLVED, APPROVED, DISAPPROVED, CLOSE;

	public static StatusEnum getStatus(String status) {
		switch (status) {
		case "New":return NEW;
		case "Assigned": return ASSIGNED;
		case "Resolved": return RESOLVED;
		case "Approved": return APPROVED;
		case "Disapproved": return DISAPPROVED;
		case "Close": return CLOSE;
		default: return NEW;
		}
	}
}
