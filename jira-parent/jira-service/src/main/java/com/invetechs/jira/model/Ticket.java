package com.invetechs.jira.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.invetechs.jira.constants.TicketPriority;
import com.invetechs.jira.constants.TicketStatus;

@Entity
@Table(name = "TICKET")
@NamedQueries({
	@NamedQuery(name = "HQL_GET_TICKETS", query = "from Ticket t where t.user.id = :userId")
})
public class Ticket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "TICKET_ID_SEQ", sequenceName = "TICKET_ID_SEQ")
	@GeneratedValue(generator = "TICKET_ID_SEQ", strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 5, max = 500)
	@Column(length = 500, nullable = false)
	private String title;
	@Size(min = 5, max = 2000)
	@Column(length = 2000, nullable = false)
	private String details;
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status = TicketStatus.OPEN;
	
	@Enumerated(EnumType.STRING)
	private TicketPriority priority = TicketPriority.Critical;
	
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY, optional = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public TicketPriority getPriority() {
		return priority;
	}

	public void setPriority(TicketPriority priority) {
		this.priority = priority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		user.addTicket(this);
		this.user = user;
	}
}
