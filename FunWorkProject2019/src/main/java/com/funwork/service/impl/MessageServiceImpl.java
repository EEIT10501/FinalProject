package com.funwork.service.impl;

import com.funwork.dao.ApplicationDao;
import com.funwork.dao.MessageDao;
import com.funwork.dao.UserDao;
import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;
import com.funwork.service.MessageService;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  MessageDao messageDao;
  @Autowired
  UserDao userDao;
  @Autowired
  ApplicationDao apDao;

  @Override
  public List<Message> getAllMessages() {
    return messageDao.getAllMessages();
  }

  @Override
  public List<Message> getOldMessageByApplicationId(Integer applicationId) {
    return messageDao.getOldMessageByApplicationId(applicationId);
  }

  @Override
  public void insertMessage(String message, Integer userId, Integer toUserId, 
      Integer apId, Integer isRead) {
    User sender = userDao.getUserById(userId);
    User receiver = userDao.getUserById(toUserId);
    Application ap = apDao.findByPrimaryKey(apId);
    Message msg = new Message();
    msg.setContent(message);
    msg.setTime(new Timestamp(System.currentTimeMillis()));
    msg.setReceiver(receiver);
    msg.setSender(sender);
    msg.setApplication(ap);
    msg.setStatus(isRead);
    messageDao.insertMessage(msg);
  }

  @Override
  public void changeMsgStatusToRead(Integer userId, Integer adId) {
    messageDao.changeMsgStatusToRead(userId, adId);
  }

  @Override
  public int getNewMsgCount(Integer userId) {
    return messageDao.getNewMsgCount(userId);
  }
}