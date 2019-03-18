package com.funwork.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Application;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.InterviewService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;

@Controller
@RequestMapping
public class ApplicationController {

	@Autowired
	InterviewService interviewService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	UserService userService;
	@Autowired
	ResumeService resumeService;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/manageApplications")
	public String pullApplicantsByUser(Model model, HttpServletRequest request) {
		System.out.println("enter manageApplications");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		List<Application> list = new LinkedList<>();
		if (user != null) {
			list = applicationService.findAllApplications(user.getUserId());
			model.addAttribute("applications", list);
			return "application/manageApplicationPage";
		} else {
			return "redirect:/";
		}
	}

}
