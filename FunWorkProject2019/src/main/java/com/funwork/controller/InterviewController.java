package com.funwork.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping(value = "/interSend")
	public String pullApplicantsByJob(Model model, HttpServletRequest req, @RequestParam("interType") String interType,
			@RequestParam("interComment") String interComment, @RequestParam("interPlace") String interPlace,
			@RequestParam("interTime") String interTime, @RequestParam("apId") String apId) {

		Interview interview = new Interview();
		interview.setInterviewType(interType);
		interview.setInterviewComment(interComment);
		interview.setInterviewPlace(interPlace);
		interview.setInterviewTime(Timestamp.valueOf(interTime.replace("T", " ") + ":00"));
		Application ap = applicationService.findByPrimaryKey(Integer.valueOf(apId));
		Integer jobId = ap.getJob().getJobId();
		interview.setApplication(ap);
		interview.setInterviewStatus("待回應");
		interviewService.saveInterview(interview);
		return "redirect:/applications?id=" + jobId;
	}

	@RequestMapping(value = "/refuseUser/{apId}/{jobId}")
	public String pullApplicantsByJob(@PathVariable("apId") String apId,@PathVariable("jobId") String jobId) {
		applicationService.refuseUser(Integer.valueOf(apId));
	
		return "redirect:/applications?id=" + jobId;
	}

}
