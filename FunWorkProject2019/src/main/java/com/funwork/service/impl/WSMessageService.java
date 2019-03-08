package com.funwork.service.impl;

import org.springframework.stereotype.Service;

import com.funwork.model.WebSocketChat;

@Service("webSocketMessageService")
public class WSMessageService {

	private WebSocketChat webSocketChat = new WebSocketChat();

	public Boolean sendToAllTerminal(String userId, String message) {

		if (webSocketChat.sendMessageToUser(userId, message)) {
			return true;
		} else {
			return false;
		}
	}

}
