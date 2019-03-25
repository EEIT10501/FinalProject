package com.funwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.service.ApplicationService;
import com.funwork.service.InterviewService;

@Controller
public class InterviewController {

	@Autowired
	InterviewService interviewService;
	@Autowired
	ApplicationService applicationService;

	@PostMapping(value = "/interSend")
	public String pullApplicantsByJob(@RequestParam("interType") String interType,
			@RequestParam("interComment") String interComment, @RequestParam("interPlace") String interPlace,
			@RequestParam("interTime") String interTime, @RequestParam("apId") Integer apId) {
		Integer jobId = interviewService.saveInterview(interType, interComment, interPlace, interTime, apId);
		Application application = applicationService.findByPrimaryKey(apId);
		applicationService.updateApplication(application);
		return "redirect:/applications?id=" + jobId;
	}

	@GetMapping(value = "/refuseUser/{apId}/{jobId}")
	public String pullApplicantsByJob(@PathVariable("apId") Integer apId, @PathVariable("jobId") String jobId) {
		applicationService.refuseUser(apId);
		return "redirect:/applications?id=" + jobId;
	}
	
	@RequestMapping(value = "/updateInterviewResult", method = RequestMethod.POST)
	  public String updateInterviewResult(@RequestParam("interviewId") Integer interviewId,
	      @RequestParam("interviewResult") String interviewResult) {

	    Interview interview = interviewService.findByPrimaryKey(interviewId);
	    interview.setInterviewResult(interviewResult);
	    interviewService.updateInterview(interview);
	    System.out.println("here");
	    return "redirect:/applicationNInterview";
	  }

}
