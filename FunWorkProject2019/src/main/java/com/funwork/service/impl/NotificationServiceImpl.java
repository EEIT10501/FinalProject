package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.NotificationDao;
import com.funwork.model.Notification;
import com.funwork.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationDao dao;

	public NotificationServiceImpl() {
	}

	@Transactional
	@Override
	public List<Notification> getAllNotifications() {
		return dao.getAllNotifications();
	}

}
