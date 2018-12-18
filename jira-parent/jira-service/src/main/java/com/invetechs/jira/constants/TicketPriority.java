package com.invetechs.jira.constants;

public enum TicketPriority {
	Blocker("Blocker"), Critical("Critical"), Major("Major"), Minor("Minor"), Trivial("Trivial");
	
	String value;
	
	private TicketPriority(String value){
		this.value = value;
	}
	
	public String getStrValue() {
		return value;
	}
}
