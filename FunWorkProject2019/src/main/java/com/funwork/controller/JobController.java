package com.funwork.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.model.Resume;
import com.funwork.model.Schedule;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.JobService;
import com.funwork.service.ScheduleService;
import com.funwork.service.NotificationService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;


@Controller
public class JobController {

	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;

	@Autowired
	ResumeService resumeService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	ScheduleService scheduleService;

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
	public String JobDetail(Model model, @PathVariable("jobId") Integer jobId,HttpServletRequest req, HttpServletResponse res) {
		Job job = jobService.getJobById(jobId);
		Resume resume = null;
		//注意
		HttpSession session = req.getSession(); //取得session物件
		User user = (User) session.getAttribute("loginUser"); //取的在session裡面名為loginUser的物件
		
		if(session.getAttribute("loginUser")!=null) {
		resume = resumeService.getResumeByUserId(user.getUserId());
		model.addAttribute("resumeBean", resume);
		}

		List<Schedule> schedulelist = scheduleService.getSchedulesByJobId(jobId);
		model.addAttribute("jobBean", job);
		
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

	@RequestMapping("/insertApplication/{userId}/{jobId}/{question}")
	public String insertApplication(Model model, @PathVariable("userId") Integer userId,
			@PathVariable("jobId") Integer jobId, @PathVariable("question") String question) {
		applicationService.insertApplication(userId, jobId, question);
		return "jobDetail";
	}

	@RequestMapping("/insertNotification/{employee}/{master}")
	public String insertNotification(Model model, @PathVariable("employee") Integer employee,
			@PathVariable("master") Integer master) {

		User emp = userService.getUserById(employee);
		User mas = userService.getUserById(master);

		Notification notification = new Notification();
		notification.setContent(emp.getUserName() + " 應徵了您的工作!");
		notification.setType(1);
		notification.setTime(new Timestamp(System.currentTimeMillis()));
		notification.setUser(emp);
		notification.setRelatedUser(mas);

		notificationService.insertNotification(notification);
		return "jobDetail";
	}

}