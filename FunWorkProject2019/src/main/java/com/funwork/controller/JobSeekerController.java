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
		List<Interview> interviewList = interviewService.findByApplicationIdAndTime(loginUser.getUserId());
		System.out.println("size"+interviewList.size());
		System.out.println("toString"+interviewList.toString());
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

	@RequestMapping("/applicatedWork")
	public String applicatedWork(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("user", loginUser);
		List<Application> applicatioList = applicationService.getApplicationByUserIdByTime(loginUser.getUserId());
		model.addAttribute("applicatioList", applicatioList);
		List<Interview> interviewList = interviewService.findByApplicationIds(loginUser.getUserId());
		model.addAttribute("interviewList", interviewList);

		return "jobSeeker/applicatedWork";
	}

//	@RequestMapping(value = "/cpApply", method = RequestMethod.POST)
//	public String complaintApply(@RequestParam("type") String type, @RequestParam("content") String content,
//			@RequestParam("jobId") Integer jobId) {
//
//		Complaint cp = new Complaint();
//		cp.setContent(content);
//		cp.setJob(jobService.getJobById(jobId));
//		cp.setStatus("待處理");
//		cp.setSubmitTime(new Timestamp(System.currentTimeMillis()));
//		cp.setType(type);
//		complaintService.insertCp(cp);
//		
//		return "redirect:/jobDetail/" + jobId;
//	}

//	@RequestMapping(value = "/interSend",)
//	public String pullApplicantsByJob(Model model, HttpServletRequest req, @RequestParam("interType") String interType,
//			@RequestParam("interComment") String interComment, @RequestParam("interPlace") String interPlace,
//			@RequestParam("interTime") String interTime, @RequestParam("apId") String apId) {
//
//		Interview interview = new Interview();
//		interview.setInterviewType(interType);
//		interview.setInterviewComment(interComment);
//		interview.setInterviewPlace(interPlace);
//		interview.setInterviewTime(Timestamp.valueOf(interTime.replace("T", " ") + ":00"));
//		Application ap = applicationService.findByPrimaryKey(Integer.valueOf(apId));
//		Integer jobId = ap.getJob().getJobId();
//		interview.setApplication(ap);
//		interview.setInterviewStatus("待回應");
//		interviewService.saveInterview(interview);
//		return "redirect:/applications?id=" + jobId;
//	}
//	
//	@RequestMapping(value = "/updateSchedule", method = RequestMethod.GET)
//	public String getUpdateScheduleForm(Model model, @RequestParam("scheduleId") Integer scheduleId) {
//		Schedule updateSchedule = scheuleService.getScheduleByPrimaryKey(scheduleId);
//		model.addAttribute("schedule", updateSchedule);
//		return "schedule/updateSchedule";
//	}
//
//	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
//	public String UpdateScheduleForm(@ModelAttribute("schedule")Schedule schedule, BindingResult result,
//			HttpServletRequest request) {
//		scheuleService.updateScheduleByPrimaryKey(schedule);
//		return "redirect:/scheduleManage";
//	}
//	@RequestMapping(value = "/updateSchedule", method = RequestMethod.POST)
//	public String UpdateScheduleForm(@ModelAttribute("schedule")Schedule schedule, BindingResult result,
//			HttpServletRequest request) {
//		scheuleService.updateScheduleByPrimaryKey(schedule);
//		return "redirect:/scheduleManage";
//	}

}
