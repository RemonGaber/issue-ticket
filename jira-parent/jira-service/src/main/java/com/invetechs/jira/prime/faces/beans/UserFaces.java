package com.invetechs.jira.prime.faces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.invetechs.jira.core.SessionUtils;
import com.invetechs.jira.exceptions.DatabaseQueryException;
import com.invetechs.jira.model.User;
import com.invetechs.jira.services.UserService;

@ManagedBean
@SessionScoped
public class UserFaces {

	@ManagedProperty("#{userService}")
	private UserService userService;
	
	private User user = new User();
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register(){
		try{
			userService.registerUser(user);
			FacesMessage facesMessage = new FacesMessage("The Employee "+this.user.getUsername() +" Is Registered Successfully");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "login?faces-redirect=true";
		}catch(DatabaseQueryException ex){
			FacesMessage facesMessage = new FacesMessage("The Employee "+this.user.getUsername() +" Failed TO Register");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			return "register?faces-redirect=true";
		}
	}
	
	public String login(){
		User user = userService.loginUser(getUser());
		if(user != null){
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("username", user.getUsername());
			return "tickets?faces-redirect=true";
		}
		return "";
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login?faces-redirect=true";
	}
}
