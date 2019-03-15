package com.funwork.controller;

import java.util.List;

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
import com.funwork.service.UserService;

@Controller
public class JobSeekerController {

	@Autowired
	UserService userService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	InterviewService interviewService;

	public JobSeekerController() {
	}

	@RequestMapping("/jobSeekerInfo")
	public String jobSeekerInfo(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);
		System.out.println(loginUser.getUserId());
		List<Application> applicatioList = applicationService.getApplicationByUserIdByTime(loginUser.getUserId());
		model.addAttribute("applicatioList", applicatioList);
//		List<Interview> interviewList = interviewService.getInterviewByApplicationId(applicatioList);
//		System.out.println(interviewList);
		return "jobSeeker/jobSeekerInfo";
	}

	@RequestMapping("/form1")
	public String form() {
		return "jobSeeker/form";
	}

}
