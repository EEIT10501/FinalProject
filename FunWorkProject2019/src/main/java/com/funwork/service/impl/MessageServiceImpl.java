package com.funwork.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.MessageDao;
import com.funwork.model.Message;
import com.funwork.model.User;
import com.funwork.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDao dao;

	public MessageServiceImpl() {
	}

	@Transactional
	@Override
	public List<Message> getAllMessages() {
		return dao.getAllMessages();
	}

	@Transactional
	@Override
	public List<Message> getOldMessageByApplicationId(Integer applicationId) {
		return dao.getOldMessageByApplicationId(applicationId);
	}

	@Transactional
	@Override
	public Serializable insertMessage(Message message) {
		return dao.insertMessage(message);
	}

	@Transactional
	@Override
	public void insertReceiver(Serializable msgId, User user) {
		dao.insertReceiver(msgId, user);
	}

	@Transactional
	@Override
	public void insertMessage(String message, String userId, String toUserId, String apId) {
		dao.insertMessage(message, userId, toUserId, apId);
	}
}
