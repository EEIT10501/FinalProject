package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Product;
import com.funwork.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	ProductService service;

	public HomeController() {
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/form")
	public String form() {
		return "pages/form";
	}

	@RequestMapping("/test")
	public String test(Model model) {

		List<Product> list = service.getAllProducts();
		model.addAttribute("productList", list);
		model.addAttribute("success", "dao&service呼叫成功");
		return "test";
	}

}
