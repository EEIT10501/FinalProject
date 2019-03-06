package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/jobs")
	public String Jobs(Model model) {
		List<Job> joblist = jobService.getJobPassed();
//		List<Job> joblist = jobService.getJobByCity(5);      //依城市搜尋
//		List<Job> joblist = jobService.getJobByCityArea(13); //依地區搜尋
		model.addAttribute("jobs", joblist);
		return "jobs";
	}
	
	@RequestMapping("/jobDetail/{jobId}")
	public String JobDetail(Model model,@PathVariable("jobId") Integer jobId) {
		Job job = jobService.getJobById(jobId);
		model.addAttribute("jobBean", job);
		return "jobDetail";
	}

}
