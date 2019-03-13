package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Application;
import com.funwork.model.Job;
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

	@RequestMapping(value = "/jobProfile")
	public String getJobPostById(@RequestParam("id") Integer id, Model model) {
		System.out.println("job selected: " + id);
		model.addAttribute("job", jobService.getJobById(id));
		return "employerManage/jobProfile";
	}

	@RequestMapping(value = "/applications")
	public String pullApplicantsByJob(@RequestParam("id") Integer id, Model model) {
		System.out.println("ready to pull applicant by jobId" + id);
		List<Application> list = applicationService.findAllApplicantsByJob(jobService.getJobById(id));
		model.addAttribute("applicantsByJob", list);
		return "employerManage/applicantsList";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.GET)
	public String getRegisterCompanyForm(Model model) {
		Job jbean = new Job();
		model.addAttribute("newJobPost", jbean);
		return "employerManage/addJobProfile";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.POST)
	public String processPostNewJob(@ModelAttribute("newJobPost") Job jbean, HttpServletRequest request) {
		System.out.println(jbean.getIndustry());
		System.out.println(jbean.getTitle());
		System.out.println(jbean.getDescription());
		System.out.println(jbean.getOther());
		System.out.println(jbean.getPostEndDate());
		System.out.println(jbean.getWorkDate());
		
		
		return "redirect:/manageJob";

	}
}
