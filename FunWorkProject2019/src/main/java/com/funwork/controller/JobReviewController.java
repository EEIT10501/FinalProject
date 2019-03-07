package com.funwork.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.service.JobService;
import com.funwork.service.NotificationService;

@Controller
public class JobReviewController {

	@Autowired
	JobService jobService;
	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = "/jobsReview", method = RequestMethod.GET)
	public String reviewJobList(Model model) {
		List<Job> jobList = jobService.getJobReviewList();
		model.addAttribute("jobList", jobList);
		return "jobreview/jobsReview";
	}

	@RequestMapping(value = "/jobReview/{jobId}", method = RequestMethod.GET)
	public String reviewJobForm(Model model, @PathVariable Integer jobId) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("jobBean", job);
		return "jobreview/jobReview";
	}

	@RequestMapping(value = "/jobReview/{jobId}", method = RequestMethod.POST)
	public String processAddNewProductForm(@PathVariable Integer jobId, @RequestParam(name = "isPass") String isPass,
			@RequestParam(name = "failReason", required = false) String failReason) {

		if (isPass.equals("pass")) {
			Job job = jobService.jobReviewPass(jobId);
			Notification notification = new Notification();
			notification.setContent("您的職缺(" + job.getTitle() + ")已通過審核");
			notification.setTime(new Timestamp(System.currentTimeMillis()));
			notification.setType(2);
			notification.setUser(job.getJobOwner());
			notificationService.insertNotification(notification);
		} else if (isPass.equals("fail")) {
			Job job = jobService.jobReviewFail(jobId, failReason);
			Notification notification = new Notification();
			notification.setContent("您的職缺(" + job.getTitle() + ")審核失敗");
			notification.setTime(new Timestamp(System.currentTimeMillis()));
			notification.setType(2);
			notification.setUser(job.getJobOwner());
			notificationService.insertNotification(notification);
		}
		return "redirect:/jobsReview";
	}
}
