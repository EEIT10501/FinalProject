package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.NotificationDao;
import com.funwork.model.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	SessionFactory factory;

	public NotificationDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getAllNotifications() {
		String hql = "FROM Notification";
		Session session = null;
		List<Notification> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}