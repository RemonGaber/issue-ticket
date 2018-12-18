package com.invetechs.jira.datamodel;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import com.invetechs.jira.model.Ticket;

public class TicketDataModel extends ListDataModel<Ticket> implements SelectableDataModel<Ticket>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketDataModel(List<Ticket> tickets){
		super(tickets);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Ticket getRowData(String rowKey) {
		List<Ticket> tickets = (List<Ticket>) getWrappedData();
		for(Ticket ticket : tickets){
			if(ticket.getId() == new Long(rowKey))
				return ticket;
		}
		return null;
	}

	@Override
	public Object getRowKey(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticket.getId();
	}

}
