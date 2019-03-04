package com.funwork.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String processAddNewProductForm(@ModelAttribute("jobBean") Job job, HttpServletRequest request) {

		System.out.println(job.getTitle());
		System.out.println(job.getComment());

		return "redirect:/jobsReview";
	}
}
