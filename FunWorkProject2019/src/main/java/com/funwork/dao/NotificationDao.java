package com.funwork.dao;

import com.funwork.model.Notification;
import java.util.List;

public interface NotificationDao {
  List<Notification> getAllNotifications();

  void insertNotification(Notification notification);
}