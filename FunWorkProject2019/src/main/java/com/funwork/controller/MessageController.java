package com.funwork.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.service.ApplicationService;
import com.funwork.service.MessageService;
import com.funwork.service.UserService;
import com.funwork.service.impl.WSMessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private WSMessageService wsMessageService;

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	@Autowired
	ApplicationService applicationService;

	@RequestMapping(value = "/TestWS", method = RequestMethod.GET)
	@ResponseBody
	public String TestWS(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "toUserId", required = true) String toUserId,
			@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "apId", required = true) String apId, HttpServletRequest req) {

		// 在此取得請求傳來的 applicationId 訊息內容 時間 傳送人及接收人id 存到資料庫
//		HttpSession session = req.getSession();
		
		messageService.insertMessage(message, userId, toUserId, apId);

		if (wsMessageService.sendToAllTerminal(toUserId, message)) {
			return "sucess";
		} else {
			return "fail";
		}
	}

}
