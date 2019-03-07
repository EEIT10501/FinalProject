package com.funwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Message;
import com.funwork.service.ApplicationService;
import com.funwork.service.MessageService;
import com.funwork.service.UserService;
import com.funwork.service.impl.WSMessageService;

@Controller
@RequestMapping
public class MessageController {

	@Autowired
	private WSMessageService wsMessageService;

	@Autowired
	MessageService messageService;
	@Autowired
	UserService userService;
	@Autowired
	ApplicationService applicationService;

	@RequestMapping(value = "/message/TestWS", method = RequestMethod.GET)
	@ResponseBody
	public String TestWS(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "toUserId", required = true) String toUserId,
			@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "apId", required = true) String apId, HttpServletRequest req) {

//		HttpSession session = req.getSession();

		messageService.insertMessage(message, userId, toUserId, apId);

		if (wsMessageService.sendToAllTerminal(toUserId, message)) {
			return "sucess";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/chat/{applicationId}")
	public String Chat(Model model, @PathVariable("applicationId") Integer applicationId) {
		List<Message> list = messageService.getOldMessageByApplicationId(applicationId);
		model.addAttribute("oldMessageList", list);
		model.addAttribute("apId", applicationId);
		return "pages/chat";
	}

	// chat2 是測試頁面，之後可以刪掉
	@RequestMapping("/chat2/{applicationId}")
	public String Chat2(Model model, @PathVariable("applicationId") Integer applicationId) {
		List<Message> list = messageService.getOldMessageByApplicationId(applicationId);
		model.addAttribute("oldMessageList", list);
		model.addAttribute("apId", applicationId);
		return "pages/chat2";
	}

}
