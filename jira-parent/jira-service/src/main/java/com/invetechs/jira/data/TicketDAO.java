package com.invetechs.jira.data;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invetechs.jira.core.SessionUtils;
import com.invetechs.jira.exceptions.DatabaseQueryException;
import com.invetechs.jira.model.Ticket;

@Transactional
@Repository("ticketDAO")
public class TicketDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void createTicket(Ticket ticket){
		try{
			getSession().persist(ticket);
		}catch(Exception ex){
			throw new DatabaseQueryException(ex.getMessage());
		}
	}
	
	public void updateTicket(Ticket ticket){
		try{
			getSession().update(ticket);
		}catch(Exception ex){
			throw new DatabaseQueryException(ex.getMessage());
		}
	}
	
	public void deleteTicket(Ticket ticket){
		try{
			getSession().delete(ticket);
		}catch(Exception ex){
			throw new DatabaseQueryException(ex.getMessage());
		}
	}
	
	public void deleteTicket(Long ticketId){
		try{
			Ticket ticket = (Ticket) getSession().load(Ticket.class, ticketId);
			getSession().delete(ticket);
		}catch(Exception ex){
			throw new DatabaseQueryException(ex.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTickets(){
		Long userId = SessionUtils.getUserId();
		Query query = getSession().getNamedQuery("HQL_GET_TICKETS");
		query.setLong("userId", userId);
		List<Ticket> tickets = query.list();
		return tickets;
	}
	
	public Ticket getTicket(Long ticketId){
		return (Ticket) getSession().get(Ticket.class, ticketId);
	}
}
