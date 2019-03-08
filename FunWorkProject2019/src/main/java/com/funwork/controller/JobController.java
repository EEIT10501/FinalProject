package com.funwork.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Resume;
import com.funwork.model.Schedule;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.JobService;
import com.funwork.service.ResumeService;
import com.funwork.service.ScheuleService;
import com.funwork.service.UserService;

@Controller
public class JobController {

	@Autowired
	JobService jobService;

	@Autowired
	ResumeService resumeService;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	ScheuleService scheduleService;

	@Autowired
	ServletContext context;

	public JobController() {

	}

	@RequestMapping("/jobs")
	public String Jobs(Model model) {
		List<Job> joblist = jobService.getJobPassed();
//		List<Job> joblist = jobService.getJobByCity(5);      //依城市搜尋
//		List<Job> joblist = jobService.getJobByCityArea(13); //依地區搜尋
		List<City> citylist = jobService.getCityName(15);
		model.addAttribute("citys", citylist);
		model.addAttribute("jobs", joblist);
		return "jobs";
	}

	@RequestMapping("/jobDetail/{jobId}")
	public String JobDetail(Model model, @PathVariable("jobId") Integer jobId) {
		Job job = jobService.getJobById(jobId);
		Resume resume = resumeService.getResumeByUserId(1); // 先寫死測試
		List<Schedule> schedulelist = scheduleService.getSchedulesByJobId(jobId);
		model.addAttribute("jobBean", job);
		model.addAttribute("resumeBean", resume);
		model.addAttribute("schedules", schedulelist);
		return "jobDetail";
	}

	@RequestMapping("/cityArea/{cityId}")
	public String cityAreaJob(Model model, @PathVariable("cityId") Integer cityId) {
		List<Job> joblist = jobService.getJobByCityArea(cityId); // 依縣市搜尋
		model.addAttribute("jobs", joblist);
		List<City> citylist = jobService.getCityName(cityId);
		model.addAttribute("citys", citylist);
		return "jobs";
	}

	@RequestMapping("/cityName/{cityId}")
	public String cityJob(Model model, @PathVariable("cityId") Integer cityId) {
		List<Job> joblist = jobService.getJobByCityName(cityId); // 依城市搜尋
		model.addAttribute("jobs", joblist);
		List<City> citylist = jobService.getCityName(cityId);
		model.addAttribute("citys", citylist);
		return "jobs";
	}

	@RequestMapping("/insertApplication/{userId}/{jobId}")
	public String cityJob(Model model, @PathVariable("userId") Integer userId, @PathVariable("jobId") Integer jobId) {
		applicationService.insertApplication(userId, jobId);
		return "jobDetail";
	}

}
