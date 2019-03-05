package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.Job;
import com.funwork.service.JobService;

@Controller
public class JobController {

	@Autowired
	JobService jobService;
	
	@Autowired
	ServletContext context;

	public JobController() {

	}

	@RequestMapping("jobs")
	public String Jobs(Model model) {
		List<Job> joblist = jobService.getAllJobs();
		model.addAttribute("jobs",joblist);
		return "jobs";
	}

}
