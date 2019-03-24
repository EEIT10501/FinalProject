package com.funwork.controller;

import java.util.HashMap;
import java.util.Iterator;
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
import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.CompanyService;
import com.funwork.service.InterviewService;
import com.funwork.service.JobService;
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
	JobService jobService;
	@Autowired
	ServletContext context;
	

	@RequestMapping(value = "/manageApplications")
	public String pullApplicantsByUser(Model model, HttpServletRequest request) {
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

	@RequestMapping(value = "/applicationNInterview")
	public String awaitResponseInterview(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		
		List<Interview> interviewsPerJobOwner = interviewService.findInterviewsByJobOwner(user);
		System.out.println("JobOwner "+user.getUserId()+"All Inteviews:");
		for(Interview interview : interviewsPerJobOwner) {
			System.out.println(interview.getInterviewId());
		}	

		model.addAttribute("interviewsPerJobOwner", interviewsPerJobOwner);

		return "application/applicationNInterview";
	}

}
