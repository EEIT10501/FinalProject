package com.funwork.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Message;
import com.funwork.model.Schedule;
import com.funwork.service.MessageService;
import com.funwork.service.ScheuleService;

@Controller
public class HomeController {

	@Autowired
	MessageService messageService;
	@Autowired
	ScheuleService scheuleService;

	public HomeController() {
	}

	@RequestMapping("/")
	public String Home() {
		return "index";
	}

	@RequestMapping("/form")
	public String Form() {
		return "pages/form";
	}

	@RequestMapping("/chat/{applicationId}")
	public String Chat(Model model, @PathVariable("applicationId") Integer applicationId) {
		List<Message> list = messageService.getOldMessageByApplicationId(applicationId);
		model.addAttribute("oldMessageList", list);
		model.addAttribute("apId", applicationId);
		return "pages/chat";
	}

	// chat2 是測試頁面，之後可以刪掉
	@RequestMapping("/chat2")
	public String Chat2(Model model) {
		List<Message> list = messageService.getOldMessageByApplicationId(1);
		model.addAttribute("oldMessageList", list);
		return "pages/chat2";
	}
	
	// 比對日期時間的範例 
	@RequestMapping("/testTime")
	public String testTime(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSSSSSS");
		Date current = new Date();
		String date = sdf.format(current);
		List<Schedule> list = scheuleService.findSchedulesGreaterThan(date);
		model.addAttribute("scheduleList", list);
		return "pages/testtime";
	}

}
