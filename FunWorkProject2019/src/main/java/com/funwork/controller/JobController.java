package com.funwork.controller;

import com.funwork.model.Application;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.JobService;
import com.funwork.service.NotificationService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobController {
  private static final String LOGIN_USER = "loginUser";
  private static final String JOB_DETAIL = "jobDetail";
  private static final String CITYS = "citys";
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
  ServletContext context;

  /**
   * Get all job is not expired.
   */
  @GetMapping("/jobs")
  public String jobs(Model model) {
    jobService.updateJobByExpired();
    List<Job> joblist = jobService.getCorrectJobs();
    model.addAttribute("jobs", joblist);
    return "jobs";
  }

  /**
   * Get job detail.
   */
  @GetMapping("/jobDetail/{jobId}")
  public String jobDetail(Model model, @PathVariable("jobId") Integer jobId, HttpSession session,
      HttpServletResponse res) {
    Resume resume = null;
    User user = (User) session.getAttribute(LOGIN_USER);
    if (session.isNew()) {
      jobService.updateViewTimesByJob(jobId);
    }
    if (user != null && resumeService.getResumeByUserId(user.getUserId()) != null) {
      resume = resumeService.getResumeByUserId(user.getUserId());
      model.addAttribute("resumeBean", resume);
    }

    if (user != null && applicationService.findByJobId(jobId) != null) {
      Application application = applicationService.isApplicantExist(user.getUserId(), jobId);
      if (application != null) {
        model.addAttribute("applicationBean", application);
      }
    }
    Job job = jobService.getJobById(jobId);
    model.addAttribute("jobBean", job);
    return JOB_DETAIL;
  }

  /**
   * Get job by cityarea.
   */
  @GetMapping("/cityArea/{cityId}")
  public String cityAreaJob(Model model, @PathVariable("cityId") Integer cityId) {
    List<Job> joblist = jobService.getJobByCityArea(cityId); // 依縣市搜尋
    model.addAttribute("jobs", joblist);
    List<City> citylist = jobService.getCityName(cityId);
    model.addAttribute(CITYS, citylist);
    return "jobs";
  }

  /**
   * Get job by cityname.
   */
  @GetMapping("/cityName/{cityId}")
  public String cityJob(Model model, @PathVariable("cityId") Integer cityId) {
    List<Job> joblist = jobService.getJobByCityName(cityId); // 依城市搜尋
    model.addAttribute("jobs", joblist);
    List<City> citylist = jobService.getCityName(cityId);
    model.addAttribute(CITYS, citylist);
    City city = jobService.getCityByPk(cityId);
    model.addAttribute("city", city);
    return "jobs";
  }

  @GetMapping("/insertApplication/{userId}/{jobId}/{question}")
  public String insertApplication(Model model, @PathVariable("userId") Integer userId,
      @PathVariable("jobId") Integer jobId, @PathVariable("question") String question) {
    applicationService.insertApplication(userId, jobId, question);
    return JOB_DETAIL;
  }

  /**
   * Insert notification of application.
   */
  @GetMapping("/insertNotification/{employee}/{master}")
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
    return JOB_DETAIL;
  }

  /**
   * Search job by title.
   */
  @GetMapping("/searchJob")
  public String searchJob(Model model, @RequestParam String searchStr) {
    List<Job> joblist = jobService.getJobsBySearchStr(searchStr);
    List<City> citylist = jobService.getCityName(15);
    model.addAttribute(CITYS, citylist);
    model.addAttribute("jobs", joblist);
    return "jobs";
  }

  @GetMapping("/jobExposure/{jobId}")
  public String jobExposure(Model model, @PathVariable Integer jobId) {
    jobService.changeJobExposure(jobId);
    return "redirect:/jobProfile?id=" + jobId;
  }

  @ResponseBody
  @GetMapping("/jobExposureCount/{userId}")
  public Integer jobExposureCount(Model model, @PathVariable Integer userId) {
    return jobService.getJobExposureCount(userId);
  }

  @GetMapping("/jobFilled/{jobId}")
  public String jobFilled(Model model, @PathVariable Integer jobId) {
    jobService.changeJobFilled(jobId);
    return "redirect:/jobProfile?id=" + jobId;
  }
}