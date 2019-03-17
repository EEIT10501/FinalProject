package com.funwork.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.funwork.model.Application;
import com.funwork.model.Complaint;
import com.funwork.model.Interview;
import com.funwork.model.Schedule;
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
		List<Application> applicatioList = applicationService.getApplicationByUserIdByTime(loginUser.getUserId());
		model.addAttribute("applicatioList", applicatioList);
		List<Interview> interviewList = interviewService.findByApplicationIdAndTimeProcessing(loginUser.getUserId());
		model.addAttribute("interviewList", interviewList);
		
		return "jobSeeker/jobSeekerInfo";
	}

	@RequestMapping(value = "/updateInterviewStatus", method = RequestMethod.POST)
	public String updateInterviewStatus(@RequestParam("interviewId") Integer interviewId,
			@RequestParam("interviewStatus") String interviewStatus) {
		System.out.println(interviewId);
		System.out.println(interviewStatus);
		Interview interview = interviewService.findByPrimaryKey(interviewId);
		interview.setInterviewStatus(interviewStatus);
		interviewService.updateInterview(interview);

		return "redirect:/jobSeekerInfo";
	}
	
	@RequestMapping("/invitationManage")
	public String invitationManage(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);
		List<Interview> interviewListProcessing = interviewService.findByApplicationIdAndTimeProcessing(loginUser.getUserId());
		List<Interview> interviewListCompleted = interviewService.findByApplicationIdAndTimeCompleted(loginUser.getUserId());
		List<Interview> interviewListExpired = interviewService.findByApplicationIdAndTimeExpired(loginUser.getUserId());
		model.addAttribute("interviewListProcessing", interviewListProcessing);
		model.addAttribute("interviewListCompleted", interviewListCompleted);
		model.addAttribute("interviewListExpired", interviewListExpired);
		
		return "jobSeeker/invitationManage"; 
	}	
	
	@RequestMapping(value = "/updateInterviewStatusOther", method = RequestMethod.POST)
	public String updateInterviewStatusOther(@RequestParam("interviewId") Integer interviewId,
			@RequestParam("interviewStatus") String interviewStatus) {
		System.out.println(interviewId);
		System.out.println(interviewStatus);
		Interview interview = interviewService.findByPrimaryKey(interviewId);
		interview.setInterviewStatus(interviewStatus);
		interviewService.updateInterview(interview);

		return "redirect:/invitationManage";
	}
	
	@RequestMapping("/applicatedWork")
	public String applicatedWork(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);
		List<Application> applicatioList = applicationService.getApplicationByUserIdByTime(loginUser.getUserId());
		model.addAttribute("applicatioList", applicatioList);

		return "jobSeeker/applicatedWork";
	}
	

}
