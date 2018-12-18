package com.invetechs.jira.prime.faces.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.invetechs.jira.constants.TicketPriority;
import com.invetechs.jira.constants.TicketStatus;
import com.invetechs.jira.datamodel.TicketDataModel;
import com.invetechs.jira.exceptions.DatabaseQueryException;
import com.invetechs.jira.model.Ticket;
import com.invetechs.jira.services.TicketService;

@ManagedBean
@SessionScoped
public class TicketFaces implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{ticketService}")
	private TicketService ticketService;
	
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private TicketDataModel ticketDataModel;
	private Ticket selectedTicket;
	private Ticket newTicket;
	@SuppressWarnings("unused")
	private TicketStatus[] status;
	@SuppressWarnings("unused")
	private TicketPriority[] priority;
	
	@PostConstruct
	public void init(){
		newTicket = new Ticket();
		selectedTicket = new Ticket();
		tickets = ticketService.getTickets();
		ticketDataModel = new TicketDataModel(tickets);
	}
	
	public TicketService getTicketService() {
		return ticketService;
	}
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public TicketDataModel getTicketDataModel() {
		return ticketDataModel;
	}

	public void setTicketDataModel(TicketDataModel ticketDataModel) {
		this.ticketDataModel = ticketDataModel;
	}

	public Ticket getSelectedTicket() {
		return selectedTicket;
	}
	public void setSelectedTicket(Ticket selectedTicket) {
		this.selectedTicket = selectedTicket;
	}
	
	public Ticket getNewTicket() {
		return newTicket;
	}
	public void setNewTicket(Ticket newTicket) {
		this.newTicket = newTicket;
	}
	public TicketStatus[] getStatus() {
		return TicketStatus.values();
	}
	
	public TicketPriority[] getPriority() {
		return TicketPriority.values();
	}

	public void setPriority(TicketPriority[] priority) {
		this.priority = priority;
	}

	public void setStatus(TicketStatus[] status) {
		this.status = status;
	}

	public String newTicketPage(){
		return "ticket?faces-redirect=true";
	}
	
	public String createTicket(){
		try{
			ticketService.create(newTicket);
			FacesMessage facesMessage = new FacesMessage("The Ticket Is Saved Successfully");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			init();
			return "tickets?faces-redirect=true";
		}catch(DatabaseQueryException ex){
			FacesMessage facesMessage = new FacesMessage("The Ticket Failed TO Save");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "";
		}
	}
	
	public String previewTicket(){
		return "preview?faces-redirect=true";
	}
	
	public String updateTicket(){
		try{
			ticketService.update(selectedTicket);
			FacesMessage facesMessage = new FacesMessage("The Ticket Is Updated Successfully");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			init();
			return "tickets?faces-redirect=true";
		}catch(DatabaseQueryException ex){
			FacesMessage facesMessage = new FacesMessage("The Ticket Failed TO Update");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "";
		}
	}
	
	public void deleteTicket(){
		try{
			ticketService.delete(selectedTicket);
			FacesMessage facesMessage = new FacesMessage("The Ticket Is Deleted Successfully");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			init();
		}catch(DatabaseQueryException ex){
			FacesMessage facesMessage = new FacesMessage("The Ticket Failed TO delete");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}
	
}
