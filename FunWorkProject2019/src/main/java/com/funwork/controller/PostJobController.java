package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Application;
import com.funwork.model.Company;
import com.funwork.service.ApplicationService;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.UserService;

@Controller
@RequestMapping
public class PostJobController {

	@Autowired
	CompanyService companyService;

	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationService applicationService;

	@Autowired
	ServletContext context;
	
	@RequestMapping(value="/jobProfile")
	public String getJobPostById(@RequestParam("id") Integer id, Model model) {
		System.out.println("job selected: "+id);
		model.addAttribute("job", jobService.getJobById(id));
		return "employerManage/jobProfile";
	}

	@RequestMapping(value="/applications")
	public String pullApplicantsByJob(@RequestParam("id") Integer id,Model model) {
		System.out.println("ready to pull applicant by jobId"+id);
		List<Application> list = applicationService.findAllApplicantsByJob
				(jobService.getJobById(id));
		model.addAttribute("applicantsByJob", list);
		return "employerManage/applicantsList";
	}
	
	
	
}
