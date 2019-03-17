package com.funwork.dao;

import java.io.Serializable;
import java.util.List;

import com.funwork.model.Message;
import com.funwork.model.User;

public interface MessageDao {
  List<Message> getAllMessages();

  List<Message> getOldMessageByApplicationId(Integer applicationId);

  void insertReceiver(Serializable msgId, User user);

  void insertMessage(String message, Integer userId, Integer toUserId, Integer apId, Integer isRead);

  void changeMsgStatusToRead(Integer userId, Integer adId);

  int getNewMsgCount(Integer userId);
}
