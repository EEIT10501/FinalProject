package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.UserService;

@Controller
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
		model.addAttribute("job", jobService.getJobById(id));
		return "employerManage/jobProfile";
	}

	@RequestMapping(value = "/applications")
	public String pullApplicantsByJob(@RequestParam("id") Integer id, Model model) {
		List<Application> list = applicationService.findAllApplicantsByJob(jobService.getJobById(id));
		model.addAttribute("applicantsByJob", list);
		return "employerManage/applicantsList";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.GET)
	public String getRegisterCompanyForm(Model model, HttpServletRequest request) {
		Job job = new Job();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		List<String> companyNameList = companyService.findAllCompanyByUser(loginUser);
		String taipeiCityNameJSON = jobService.getCityNameList("台北市");
		String newTaipeiCityNameJSON = jobService.getCityNameList("新北市");
		model.addAttribute("jobBean", job);
		model.addAttribute("taipeiCityNameJSON", taipeiCityNameJSON);
		model.addAttribute("newTaipeiCityNameJSON", newTaipeiCityNameJSON);
		model.addAttribute("companyNameList", companyNameList);
		return "employerManage/addJobProfile";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.POST)
	public String processPostNewJob(@ModelAttribute("jobBean") Job jbean, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		jobService.insertJob(jbean, loginUser.getUserId());
		return "redirect:/manageJob";
	}

	@RequestMapping(value = "/getJobPostedCount/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getJobPostedCount(@PathVariable("userId") Integer userId) {
		Integer count = jobService.getJobPostedCount(userId);
		return count;
	}

}
