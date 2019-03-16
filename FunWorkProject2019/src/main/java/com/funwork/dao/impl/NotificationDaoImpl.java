package com.funwork.dao.impl;

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

  @SuppressWarnings("unchecked")
  @Override
  public List<Notification> getAllNotifications() {
    String hql = "FROM Notification";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public void insertNotification(Notification notification) {
    Session session = factory.getCurrentSession();
    session.save(notification);
  }

}
