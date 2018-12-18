package com.invetechs.jira.constants;

public enum TicketStatus {
	OPEN("OPEN"), CLOSED("CLOSED"), IN_PROGRESS("IN PROGRESS"), DONE("DONE");
	
	String value;
	
	private TicketStatus(String value){
		this.value = value;
	}
	
	public String getStrValue() {
		return value;
	}
}
