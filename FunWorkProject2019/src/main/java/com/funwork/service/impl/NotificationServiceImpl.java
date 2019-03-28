package com.funwork.service.impl;

import com.funwork.dao.NotificationDao;
import com.funwork.model.Notification;
import com.funwork.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired
  NotificationDao dao;

  @Override
  public void insertNotification(Notification notification) {
    dao.insertNotification(notification);
  }
}