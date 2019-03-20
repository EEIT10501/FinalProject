package com.funwork.service;

import com.funwork.model.Message;
import java.util.List;

public interface MessageService {
  List<Message> getAllMessages();

  List<Message> getOldMessageByApplicationId(Integer applicationId);

  void insertMessage(String message, Integer userId, Integer toUserId, 
      Integer apId, Integer isRead);

  void changeMsgStatusToRead(Integer userId, Integer adId);

  int getNewMsgCount(Integer userId);
}