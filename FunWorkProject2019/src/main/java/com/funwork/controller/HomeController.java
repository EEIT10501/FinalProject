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

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	MessageService messageService;

	@Autowired
	JobService jobService;

	@Autowired
	ScheuleService scheduleService;

	@Autowired
	SalaryService salaryService;

	@Autowired
	AttendenceService attendenceService;

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

	@RequestMapping("/test")
	public String Test(Model model) {

		List<Product> productList = productService.getAllProducts();
		List<Order> orderList = orderService.getAllOrders();
		List<Notification> notificationList = notificationService.getAllNotifications();
		List<Message> messageList = messageService.getAllMessages();
		List<Job> jobList = jobService.getAllJobs();
		List<Schedule> scheduleList = scheduleService.getAllSchedules();
		List<Salary> salaryList = salaryService.getAllSalarys();
		List<Attendence> attendenceList = attendenceService.getAllAttendences();

		model.addAttribute("productList", productList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("notificationList", notificationList);
		model.addAttribute("messageList", messageList);
		model.addAttribute("jobList", jobList);
		model.addAttribute("success", "dao&service呼叫成功");
		model.addAttribute("scheduleList", scheduleList);
		model.addAttribute("salaryList", salaryList);
		model.addAttribute("attendenceList", attendenceList);
		return "test";
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
