package com.funwork.service.impl;

import com.funwork.dao.MessageDao;
import com.funwork.model.Message;
import com.funwork.service.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  MessageDao dao;

  @Override
  public List<Message> getAllMessages() {
    return dao.getAllMessages();
  }

  @Override
  public List<Message> getOldMessageByApplicationId(Integer applicationId) {
    return dao.getOldMessageByApplicationId(applicationId);
  }

  @Override
  public void insertMessage(String message, Integer userId, Integer toUserId, 
      Integer apId, Integer isRead) {
    dao.insertMessage(message, userId, toUserId, apId, isRead);
  }

  @Override
  public void changeMsgStatusToRead(Integer userId, Integer adId) {
    dao.changeMsgStatusToRead(userId, adId);
  }

  @Override
  public int getNewMsgCount(Integer userId) {
    return dao.getNewMsgCount(userId);
  }
}