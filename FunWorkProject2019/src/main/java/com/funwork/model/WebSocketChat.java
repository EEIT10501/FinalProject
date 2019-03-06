package com.funwork.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	// 紀錄每個用戶多個終端的連線
	private static Map<String, Set<WebSocketChat>> userSocket = new HashMap<>();

	// 需要session來對用戶發送訊息，獲取連線userId
	private Session session;
	private String userId;

	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
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

	@OnClose
	public void onClose() {
		if (userSocket.get(this.userId).size() == 0) {
			userSocket.remove(this.userId);
		} else {
			userSocket.get(this.userId).remove(this);
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		if (session == null) {
			System.out.println("session null");
		}
		sendMessageToUser(this.userId, message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("userID為：{" + this.userId + "}的連線發生錯誤");
		error.printStackTrace();
	}

	public Boolean sendMessageToUser(String userId, String message) {
		if (userSocket.containsKey(userId)) {
			for (WebSocketChat WS : userSocket.get(userId)) {
				try {
					WS.session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println(" 給userId為：{" + userId + "}發送訊息失敗");
					return false;
				}
			}
			return true;
		}
		System.out.println("發送錯誤：目前連線不包含id：{" + userId + "}的用户");
		return false;
	}
}