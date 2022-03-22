package com.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.entity.UserLogin;



@Component
public class UserDao {
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	 @Autowired
	 private SessionFactory factory;
	
	@Transactional
	public int save(User u) {
		Integer i = (Integer)this.hibernateTemplate.save(u);
		return i;
	}
	public List<User> getAll(){
		List<User> users = this.hibernateTemplate.loadAll(User.class);
		return users;
	}
	@Transactional
	public User get(String uname) {
		return hibernateTemplate.get(User.class, uname);
	}
	
}

