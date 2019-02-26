package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.MessageDao;
import com.funwork.model.Message;
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

}
