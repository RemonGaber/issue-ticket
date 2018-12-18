package com.invetechs.jira.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(name = "UNIQUE_USER_NAME", columnNames = {"username"})})
@NamedQuery(name = "HQL_GET_USER", query = "from User u where u.username = :username and u.password = :password")
public class User {

	@Id
	@SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
	@GeneratedValue(generator = "USER_ID_SEQ", strategy = GenerationType.AUTO)
	private Long id;
	@Size(min = 3, max = 20)
	@Column(nullable = false)
	private String username;
	@Size(min = 3, max = 30)
	@Column(nullable = false)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "user")
	private List<Ticket> tickets;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Ticket> getTickets() {
		if(tickets == null){
			tickets = new ArrayList<Ticket>();
		}
		return tickets;
	}
	public void addTicket(Ticket ticket){
		getTickets().add(ticket);
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
