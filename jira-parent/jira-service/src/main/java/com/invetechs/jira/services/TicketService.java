package com.invetechs.jira.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invetechs.jira.core.SessionUtils;
import com.invetechs.jira.data.TicketDAO;
import com.invetechs.jira.data.UserDAO;
import com.invetechs.jira.model.Ticket;
import com.invetechs.jira.model.User;

@Transactional
@Service("ticketService")
public class TicketService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public void create(Ticket ticket){
		Long userId = SessionUtils.getUserId();
		User user = userDAO.getUserById(userId);
		ticket.setUser(user);
		ticketDAO.createTicket(ticket);
	}
	
	public void update(Ticket ticket){
		ticketDAO.updateTicket(ticket);
	}
	
	public void delete(Ticket ticket){
		ticketDAO.deleteTicket(ticket);
	}
	
	public void delete(Long ticketId){
		ticketDAO.deleteTicket(ticketId);
	}
	
	public List<Ticket> getTickets(){
		return ticketDAO.getTickets();
	}
	
	public Ticket getTicket(Long ticketId){
		return ticketDAO.getTicket(ticketId);
	}
}
