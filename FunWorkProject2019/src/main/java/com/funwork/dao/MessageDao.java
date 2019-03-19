package com.funwork.dao;

import com.funwork.model.Message;
import java.util.List;

public interface MessageDao {
  List<Message> getAllMessages();

  List<Message> getOldMessageByApplicationId(Integer applicationId);

  public void insertMessage(Message msg);

  void changeMsgStatusToRead(Integer userId, Integer adId);

  int getNewMsgCount(Integer userId);
}
