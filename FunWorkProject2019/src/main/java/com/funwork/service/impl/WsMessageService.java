package com.funwork.service.impl;

import com.funwork.model.WebSocketChat;
import org.springframework.stereotype.Service;

@Service("webSocketMessageService")
public class WsMessageService {

  private WebSocketChat webSocketChat = new WebSocketChat();

  public Boolean sendToAllTerminal(String userId, String message) {
    return webSocketChat.sendMessageToUser(userId, message);
  }
}
