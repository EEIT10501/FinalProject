package com.funwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Application;
import com.funwork.model.Message;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.MessageService;
import com.funwork.service.impl.WSMessageService;

@Controller
public class MessageController {

	@Autowired
	private WSMessageService wsMessageService;
	@Autowired
	MessageService messageService;
	@Autowired
	ApplicationService applicationService;

	@RequestMapping(value = "/message/TestWS", method = RequestMethod.GET)
	@ResponseBody
	public String testWS(@RequestParam(value = "userId", required = true) Integer userId,
			@RequestParam(value = "toUserId", required = true) Integer toUserId,
			@RequestParam(value = "message", required = true) String message,
			@RequestParam(value = "apId", required = true) Integer apId) {
		applicationService.updateLatestMsg(apId, message);
		messageService.insertMessage(message, userId, toUserId, apId, 0);
		if (wsMessageService.sendToAllTerminal(toUserId.toString(), message)) {
			return "sucess";
		} else {
			return "fail";
		}
	}

	@RequestMapping("/chat")
	public String chatList(Model model, HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 0);
		res.setHeader("Pragma", "no-cache");
		return "pages/chatList";
	}

	@RequestMapping("/chat/{applicationId}")
	public String chat(Model model, @PathVariable("applicationId") Integer applicationId, HttpServletRequest req,
			HttpServletResponse res) {
		HttpSession session = req.getSession();
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Cache-Control", "no-store");
		res.setDateHeader("Expires", 0);
		res.setHeader("Pragma", "no-cache");

		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/";
		}

		User user = null;
		User toUser = null;
		List<Message> list = messageService.getOldMessageByApplicationId(applicationId);
		Application application = applicationService.findByPrimaryKey(applicationId);
		if (loginUser.getUserId() == application.getUser().getUserId()) {
			user = application.getUser();
			toUser = application.getJob().getJobOwner();
		} else {
			user = application.getJob().getJobOwner();
			toUser = application.getUser();
		}
		model.addAttribute("oldMessageList", list);
		model.addAttribute("application", application);
		model.addAttribute("user", user);
		model.addAttribute("toUser", toUser);
		return "pages/chat";
	}

	@RequestMapping(value = "/chatJSON", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Message>> getOldMsgByApId(@RequestParam("apId") Integer apId, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		messageService.changeMsgStatusToRead(loginUser.getUserId(), apId);
		List<Message> list = messageService.getOldMessageByApplicationId(apId);
		ResponseEntity<List<Message>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}

	@RequestMapping(value = "/apJSON", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<Application>> getApByUserId(@RequestParam("userId") Integer userId) {
		List<Application> list = applicationService.getApplicationByUserId(userId);
		ResponseEntity<List<Application>> re = new ResponseEntity<>(list, HttpStatus.OK);
		return re;
	}

	@RequestMapping("/newMsg")
	@ResponseBody
	public int newMsg(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		int count;
		if (loginUser == null) {
			count = 0;
		} else {
			count = messageService.getNewMsgCount(loginUser.getUserId());
		}
		return count;
	}
}
