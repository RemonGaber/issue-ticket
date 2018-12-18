package com.invetechs.jira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invetechs.jira.data.UserDAO;
import com.invetechs.jira.model.User;

@Transactional
@Service("userService")
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public void registerUser(User user){
		userDAO.register(user);
	}
	
	public User loginUser(User user){
		return userDAO.login(user);
	}
}
