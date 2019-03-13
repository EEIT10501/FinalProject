package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Application;
import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.model.User;
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

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody String processPostNewJob(@ModelAttribute("newJobPost") Model model,BindingResult result, 
			HttpServletRequest request) {
		System.out.println("here");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		Integer times = user.getJobPostLimit();
		List<Job> list = jobService.findJobByUserIdNJobStatus(user.getUserId());
		int activeJobNumber = list.size();
		if (user != null && activeJobNumber < 3) {
			return "OK";
		} else {
			return "quotaMeet";
		}
	}
}
