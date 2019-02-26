package com.funwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.service.impl.WSMessageService;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private WSMessageService wsMessageService;

	@RequestMapping(value = "/TestWS", method = RequestMethod.GET)
	@ResponseBody
	public String TestWS(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "message", required = true) String message) {
		System.out.println("收到發送請求，向用户{" + userId + "}發送的消息：{" + message + "}");

		if (wsMessageService.sendToAllTerminal(userId, message)) {
			return "sucess";
		} else {
			return "fail";
		}
	}
}
