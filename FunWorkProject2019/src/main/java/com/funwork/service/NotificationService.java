package com.funwork.service;

import java.util.List;

import com.funwork.model.Notification;

public interface NotificationService {
	List<Notification> getAllNotifications();
	
	void insertNotification(Notification notification);
}
