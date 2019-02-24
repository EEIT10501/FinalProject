package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.MessageDao;
import com.funwork.model.Message;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	SessionFactory factory;

	public MessageDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAllMessages() {
		String hql = "FROM Message";
		Session session = null;
		List<Message> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}