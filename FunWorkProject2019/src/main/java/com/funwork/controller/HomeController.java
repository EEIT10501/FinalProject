package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Job;
import com.funwork.model.Message;
import com.funwork.model.Notification;
import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.MessageService;
import com.funwork.service.NotificationService;
import com.funwork.service.OrderService;
import com.funwork.service.ProductService;

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
		model.addAttribute("productList", productList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("notificationList", notificationList);
		model.addAttribute("messageList", messageList);
		model.addAttribute("jobList", jobList);
		model.addAttribute("success", "dao&service呼叫成功");
		return "test";
	}
	
}
