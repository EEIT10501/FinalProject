package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.UserDao;
import com.funwork.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory factory;

	public UserDaoImpl() {

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		String hql = "FROM User";

		Session session = null;
		List<User> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public User getUserById(Integer userId) {
		Session session = factory.getCurrentSession();
		User user = session.get(User.class, userId);
		return user;
	}

}
