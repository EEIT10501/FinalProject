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
	public void insertMessage(String message, Integer userId, Integer toUserId, Integer apId, Integer isRead) {
		dao.insertMessage(message, userId, toUserId, apId, isRead);
	}

	@Transactional
	@Override
	public void changeMsgStatusToRead(Integer userId, Integer adId) {
		dao.changeMsgStatusToRead(userId, adId);
	}

	@Transactional
	@Override
	public int getNewMsgCount(Integer userId) {
		return dao.getNewMsgCount(userId);
	}
}
