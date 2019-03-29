package com.funwork.controller;

import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.InterviewService;
import com.funwork.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobSeekerController {
  private static final String LOGIN_USER = "loginUser";
  private static final String REDIRECT_TO_INDEX = "redirect:/";
  @Autowired
  UserService userService;
  @Autowired
  ApplicationService applicationService;
  @Autowired
  InterviewService interviewService;

  /**
   * Get jobseeker info.
   */
  @GetMapping("/jobSeekerInfo")
  public String jobSeekerInfo(Model model, HttpSession session) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      model.addAttribute("user", loginUser);
      List<Application> applicatioList = applicationService
          .getApplicationByUserIdByTime(loginUser.getUserId());
      model.addAttribute("applicatioList", applicatioList);
      List<Interview> interviewList = interviewService
          .findByApplicationIdAndTimeProcessing(loginUser.getUserId());
      model.addAttribute("interviewList", interviewList);
      return "jobSeeker/jobSeekerInfo";
    } else {
      return REDIRECT_TO_INDEX;
    }

  }

  /**
   * Get invitation.
   */
  @GetMapping("/invitationManage")
  public String invitationManage(Model model, HttpSession session) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      model.addAttribute("user", loginUser);
      List<Interview> interviewListProcessing = interviewService
          .findByApplicationIdAndTimeProcessing(loginUser.getUserId());
      List<Interview> interviewListCompleted = interviewService
          .findByApplicationIdAndTimeCompleted(loginUser.getUserId());
      List<Interview> interviewListExpired = interviewService
          .findByApplicationIdAndTimeExpired(loginUser.getUserId());
      model.addAttribute("interviewListProcessing", interviewListProcessing);
      model.addAttribute("interviewListCompleted", interviewListCompleted);
      model.addAttribute("interviewListExpired", interviewListExpired);
      return "jobSeeker/invitationManage";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Update interview status.
   */
  @PostMapping(value = "/updateInterviewStatus")
  public String updateInterviewStatus(@RequestParam("interviewId") Integer interviewId,
      @RequestParam("interviewStatus") String interviewStatus) {
    Interview interview = interviewService.findByPrimaryKey(interviewId);
    interview.setInterviewStatus(interviewStatus);
    interviewService.updateInterview(interview);
    return "redirect:/invitationManage";
  }

  /**
   * Get appliction record.
   */
  @GetMapping("/applicatedWork")
  public String applicatedWork(Model model, HttpSession session) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    if (loginUser != null) {
      List<Application> applicatioList = applicationService
          .getApplicationByUserIdByTime(loginUser.getUserId());
      model.addAttribute("applicatioList", applicatioList);
      return "jobSeeker/applicatedWork";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }
}