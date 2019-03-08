package com.funwork.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.MessageDao;
import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getOldMessageByApplicationId(Integer applicationId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Message WHERE Fk_Application_Id = :applicationId ORDER BY time ASC";
		List<Message> list = session.createQuery(hql).setParameter("applicationId", applicationId).getResultList();
		return list;
	}

	@Override
	public Serializable insertMessage(Message message) {
		Session session = factory.getCurrentSession();
		Serializable id = session.save(message);
		return id;
	}

	@Override
	public void insertReceiver(Serializable msgId, User receiver) {
		Session session = factory.getCurrentSession();
		Message msg = session.get(Message.class, msgId);
		msg.setReceiver(receiver);
	}

	@Override
	public void insertMessage(String message, String userId, String toUserId, String apId) {
		Session session = factory.getCurrentSession();
		Application ap = session.get(Application.class, Integer.valueOf(apId));
		User sender = session.get(User.class, Integer.valueOf(userId));
		User receiver = session.get(User.class, Integer.valueOf(toUserId));
		Message msg = new Message();
		msg.setContent(message);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		msg.setReceiver(receiver);
		msg.setSender(sender);
		msg.setApplication(ap);
		session.save(msg);
	}

}
