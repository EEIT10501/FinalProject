package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Job;
import com.funwork.service.JobService;

@Controller
public class JobReviewController {

	@Autowired
	JobService jobService;

	@GetMapping(value = "/jobsReview")
	public String reviewJobList(Model model) {
		List<Job> jobList = jobService.getJobReviewList();
		model.addAttribute("jobList", jobList);
		return "jobreview/jobsReview";
	}

	@GetMapping(value = "/jobReview/{jobId}")
	public String reviewJobForm(Model model, @PathVariable Integer jobId) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("jobBean", job);
		return "jobreview/jobReview";
	}

	@PostMapping(value = "/jobReview/{jobId}")
	public String processAddNewProductForm(@PathVariable Integer jobId, @RequestParam(name = "isPass") String isPass,
			@RequestParam(name = "failReason", required = false) String failReason) {
		if (isPass.equals("pass")) {
			jobService.jobReviewPass(jobId);
		} else if (isPass.equals("fail")) {
			jobService.jobReviewFail(jobId, failReason);
		}
		return "redirect:/jobsReview";
	}

	@GetMapping(value = "/jobsReviewHistory")
	public String reviewJobsHistory(Model model) {
		List<Job> jobList = jobService.getReviewHistory();
		model.addAttribute("jobList", jobList);
		return "jobreview/jobsReviewHistory";
	}

	@GetMapping(value = "/jobReviewHistory/{jobId}")
	public String reviewJobHistory(Model model, @PathVariable Integer jobId) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("jobBean", job);
		return "jobreview/jobReviewHistory";
	}
}
