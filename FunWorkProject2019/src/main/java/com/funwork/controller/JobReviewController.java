package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Job;
import com.funwork.service.JobService;

@Controller
public class JobReviewController {

	@Autowired
	JobService jobService;

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
			jobService.jobReviewPass(jobId);
		} else if (isPass.equals("fail")) {
			jobService.jobReviewFail(jobId, failReason);
		}
		return "redirect:/jobsReview";
	}

	@RequestMapping(value = "/jobsReviewHistory", method = RequestMethod.GET)
	public String reviewJobsHistory(Model model) {
		List<Job> jobList = jobService.getReviewHistory();
		model.addAttribute("jobList", jobList);
		return "jobreview/jobsReviewHistory";
	}

	@RequestMapping(value = "/jobReviewHistory/{jobId}", method = RequestMethod.GET)
	public String reviewJobHistory(Model model, @PathVariable Integer jobId) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("jobBean", job);
		return "jobreview/jobReviewHistory";
	}
}
