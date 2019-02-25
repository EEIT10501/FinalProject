package com.funwork.controller;

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

// websocket連線URL和可被調用配置
@ServerEndpoint(value = "/chat/{userId}", configurator = SpringConfigurator.class)
public class WebSocketChat {

	// 紀錄目前連線數量
	private static int onlineCount = 0;

	// 紀錄每個用戶多個終端的連線
	private static Map<String, Set<WebSocketChat>> userSocket = new HashMap<>();

	// 需要session來對用戶發送訊息，獲取連線userId
	private Session session;
	private String userId;

	/**
	 * @Title: onOpen
	 * @Description: websocekt連線建立時的操作
	 * @param @param userId
	 * @param @param session websocket連線的session属性
	 * @param @throws IOException
	 */
	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session session) throws IOException {
		this.session = session;
		this.userId = userId;
		onlineCount++;
		// 根據該用戶當前是否已經在別的終端登錄進行添加操作
		if (userSocket.containsKey(this.userId)) {
			System.out.println("目前userId:{" + this.userId + "}已有其他終端登錄");
			userSocket.get(this.userId).add(this); // 增加該用戶set中的連線實例
		} else {
			System.out.println("目前userId:{" + this.userId + "}是第一個終端登錄");
			Set<WebSocketChat> addUserSet = new HashSet<>();
			addUserSet.add(this);
			userSocket.put(this.userId, addUserSet);
		}
		System.out.println("用户{" + userId + "}登錄的終端個數為{" + userSocket.get(this.userId).size() + "}");
		System.out.println("目前在線用戶數為：{" + userSocket.size() + "}，所有終端個數為：{" + onlineCount + "}");
	}

	/**
	 * @Title: onClose
	 * @Description: 連線關閉的操作
	 */
	@OnClose
	public void onClose() {
		// 移除當前用戶終端登錄的websocket訊息，如果該用戶所有的終端都下線了，則刪除該用戶的紀錄
		if (userSocket.get(this.userId).size() == 0) {
			userSocket.remove(this.userId);
		} else {
			userSocket.get(this.userId).remove(this);
		}
		System.out.println("用户{" + this.userId + "}登錄的終端個數為{" + userSocket.get(this.userId).size() + "}");
		System.out.println("目前在線用戶數為：{" + userSocket.size() + "}，所有終端個數為：{" + onlineCount + "}");
	}

	/**
	 * @Title: onMessage
	 * @Description: 收到訊息後的操作
	 * @param @param message 收到的訊息
	 * @param @param session 該連線的session屬性
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("收到來自userId：{" + this.userId + "}的訊息：{" + message + "}");
		if (session == null)
			System.out.println("session null");
		// 測試向客戶端發送訊息
		 sendMessageToUser(this.userId, message);
	}

	/**
	 * @Title: onError
	 * @Description: 連線發生錯誤時的操作
	 * @param @param session 該連線的session
	 * @param @param error 發生的錯誤
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("userID為：{" + this.userId + "}的連線發生錯誤");
		error.printStackTrace();
	}

	/**
	 * @Title: sendMessageToUser
	 * @Description: 發送訊息給用戶的所有終端
	 * @param @param userId 用户id
	 * @param @param message 發送的訊息
	 * @param @return 發送成功返回true，失敗返回false
	 */
	public Boolean sendMessageToUser(String userId, String message) {
		if (userSocket.containsKey(userId)) {
			System.out.println(" 給userId為：{" + userId + "}的所有終端發送訊息：{" + message + "}");
			for (WebSocketChat WS : userSocket.get(userId)) {
				System.out.println("sessionId為:{" + WS.session.getId() + "}");
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
