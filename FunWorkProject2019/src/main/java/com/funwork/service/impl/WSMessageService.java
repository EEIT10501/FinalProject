package com.funwork.service.impl;

import org.springframework.stereotype.Service;

import com.funwork.model.WebSocketChat;

@Service("webSocketMessageService")
public class WSMessageService {

	private WebSocketChat webSocketChat = new WebSocketChat();


	/**
	 * @Title: sendToAllTerminal
	 * @Description: 调用websocket类给用户下的所有终端发送消息
	 * @param @param userId 用户id
	 * @param @param message 消息
	 * @param @return 发送成功返回true，否则返回false
	 */
	public Boolean sendToAllTerminal(String userId, String message) {
		System.out.println("向用户{" + userId + "}的消息：{" + message + "}");
		// 不論對方是否在線，都將訊息存到資料庫
		if (webSocketChat.sendMessageToUser(userId, message)) {
			return true;
		} else {
			return false;
		}
	}

}
