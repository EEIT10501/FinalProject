package com.funwork.dao.impl;

import com.funwork.dao.NotificationDao;
import com.funwork.model.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDaoImpl implements NotificationDao {

  @Autowired
  SessionFactory factory;

  @Override
  public void insertNotification(Notification notification) {
    Session session = factory.getCurrentSession();
    session.save(notification);
  }

}
