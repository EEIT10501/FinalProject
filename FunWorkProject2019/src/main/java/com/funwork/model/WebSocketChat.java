package com.funwork.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

@ServerEndpoint(value = "/chat/{userId}", configurator = SpringConfigurator.class)
public class WebSocketChat {
  public static final Logger logger = Logger.getLogger("com.funwork");
  private static Map<String, Set<WebSocketChat>> userSocket = new HashMap<>();
  private Session session;
  private String userId;

  /**
   * When ws connection on open, call this method.
   */
  @OnOpen
  public void onOpen(@PathParam("userId") String userId, Session session) {
    this.session = session;
    this.userId = userId;

    if (userSocket.containsKey(this.userId)) {

      userSocket.get(this.userId).add(this);
    } else {
      Set<WebSocketChat> addUserSet = new HashSet<>();
      addUserSet.add(this);
      userSocket.put(this.userId, addUserSet);
    }

  }

  /**
   * When ws connection close, call this method.
   */
  @OnClose
  public void onClose() {

    if (userSocket.get(this.userId).isEmpty()) {
      userSocket.remove(this.userId);
    } else {
      userSocket.get(this.userId).remove(this);
    }
  }

  /**
   * When sever get message, call this method.
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    if (session == null) {
      logger.warning("session is null");
    }
    sendMessageToUser(this.userId, message);
  }

  @OnError
  public void onError(Session session, Throwable error) {
    logger.log(Level.WARNING, "userId為：{0}的連線發生錯誤", this.userId);
    logger.warning(error.getMessage());
  }

  /**
   * Send message to user.
   */
  public Boolean sendMessageToUser(String userId, String message) {
    if (userSocket.containsKey(userId)) {

      for (WebSocketChat ws : userSocket.get(userId)) {
        try {
          ws.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
          logger.log(Level.WARNING, "給userID為：{0}發送訊息失敗", userId);
          logger.warning(e.getMessage());
          return false;
        }
      }
      return true;
    }
    return false;
  }
}