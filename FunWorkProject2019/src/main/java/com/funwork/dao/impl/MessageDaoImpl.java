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
	public void insertMessage(String message, Integer userId, Integer toUserId, Integer apId, Integer isRead) {
		Session session = factory.getCurrentSession();
		Application ap = session.get(Application.class, apId);
		User sender = session.get(User.class, userId);
		User receiver = session.get(User.class, toUserId);
		Message msg = new Message();
		msg.setContent(message);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		msg.setReceiver(receiver);
		msg.setSender(sender);
		msg.setApplication(ap);
		msg.setStatus(isRead);
		session.save(msg);
	}

	@Override
	public void changeMsgStatusToRead(Integer userId, Integer adId) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE Message m SET m.status = 1 WHERE m.receiver.userId = :userId AND m.application.applicationId = :apId";
		session.createQuery(hql).setParameter("userId", userId).setParameter("apId", adId).executeUpdate();
	}

	@Override
	public int getNewMsgCount(Integer userId) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(*) FROM Message m where m.receiver.userId = :userId and m.status = 0";
		Long count = (Long) session.createQuery(hql).setParameter("userId", userId).uniqueResult();

		return count.intValue();
	}

}
