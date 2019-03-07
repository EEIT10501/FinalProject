package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Attendence;
import com.funwork.model.Job;
import com.funwork.model.Message;
import com.funwork.model.Notification;
import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.model.Salary;
import com.funwork.model.Schedule;
import com.funwork.service.AttendenceService;
import com.funwork.service.JobService;
import com.funwork.service.MessageService;
import com.funwork.service.NotificationService;
import com.funwork.service.OrderService;
import com.funwork.service.ProductService;
import com.funwork.service.SalaryService;
import com.funwork.service.ScheuleService;

@Controller
public class HomeController {

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

	@RequestMapping("/chat")
	public String Chat(Model model) {
		// 在這邊抓當前User的歷史訊息傳到訊息頁面
		model.addAttribute("oldMessage", "54321 : 嗨 ( 2019 年 2 月 23 日 )");
		return "pages/chat";
	}

	// chat2 是測試頁面，之後可以刪掉
	@RequestMapping("/chat2")
	public String Chat2(Model model) {
		model.addAttribute("oldMessage", "54321 : 嗨 ( 2019 年 2 月 23 日 )");
		return "pages/chat2";
	}

}
