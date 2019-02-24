package com.funwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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

}
