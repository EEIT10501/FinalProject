package com.funwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Message;
import com.funwork.service.MessageService;
import com.funwork.service.impl.WSMessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private WSMessageService wsMessageService;

	@Autowired
	MessageService messageService;

	@RequestMapping(value = "/TestWS", method = RequestMethod.GET)
	@ResponseBody
	public String TestWS(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "message", required = true) String message, HttpServletRequest req) {
		System.out.println("收到發送請求，向用户{" + userId + "}發送的消息：{" + message + "}");

		// 在此取得請求傳來的 applicationId 訊息內容 時間 傳送人及接收人id 存到資料庫
		HttpSession session = req.getSession();
		String sessionId = session.getId();
		List<Message> list = messageService.getAllMessages();
		System.out.println("使用service成功!!!" + list.size());
		System.out.println("sessionId = " + sessionId);

		if (wsMessageService.sendToAllTerminal(userId, message)) {
			return "sucess";
		} else {
			return "fail";
		}
	}
}
