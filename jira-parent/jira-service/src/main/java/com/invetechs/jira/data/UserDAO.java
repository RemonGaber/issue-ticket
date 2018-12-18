package com.invetechs.jira.data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.invetechs.jira.exceptions.DatabaseQueryException;
import com.invetechs.jira.model.User;

@Transactional
@Repository("userDAO")
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public User getUserById(Long userId){
		return (User) getSession().get(User.class, userId);
	}
	
	public void register(User user){
		try{
			getSession().persist(user);
		}catch(Exception ex){
			throw new DatabaseQueryException(ex.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public User login(User user){
		Query query = getSession().getNamedQuery("HQL_GET_USER");
		query.setString("username", user.getUsername());
		query.setString("password", user.getPassword());
		List<User> users = query.list();
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}
}
