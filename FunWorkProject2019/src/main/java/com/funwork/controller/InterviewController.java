package com.funwork.controller;

import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.InterviewService;
import com.funwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InterviewController {
  @Autowired
  InterviewService interviewService;
  @Autowired
  ApplicationService applicationService;
  @Autowired
  UserService userService;

  /**
   * Send interview.
   */
  @PostMapping(value = "/interSend")
  public String pullApplicantsByJob(@RequestParam("interType") String interType,
      @RequestParam("interComment") String interComment, 
      @RequestParam("interPlace") String interPlace,
      @RequestParam("interTime") String interTime, @RequestParam("apId") Integer apId) {
    Integer jobId = interviewService
        .saveInterview(interType, interComment, interPlace, interTime, apId);
    Application application = applicationService.findByPrimaryKey(apId);
    applicationService.updateApplication(application);
    return "redirect:/applications?id=" + jobId;
  }

  @GetMapping(value = "/refuseUser/{apId}/{jobId}")
  public String pullApplicantsByJob(@PathVariable("apId") Integer apId, 
      @PathVariable("jobId") String jobId) {
    applicationService.refuseUser(apId);
    return "redirect:/applications?id=" + jobId;
  }

  /**
   * update interviewResult.
   */
  @PostMapping(value = "/updateInterviewResult")
  public String updateInterviewResult(@RequestParam("interviewId") Integer interviewId,
      @RequestParam("interviewResult") String interviewResult,
      @RequestParam(value = "interviewRating", required = false) String interviewRating) {
    Interview interview = interviewService.findByPrimaryKey(interviewId);
    Application app = applicationService
        .findByPrimaryKey(interview.getApplication().getApplicationId());
    User user = app.getUser();
    Double rating = user.getRating();
    Double newRating = null;
    if (interviewRating != null) {
      newRating = Double.valueOf(interviewRating);
    } else {
      newRating = 0.0;
    }
    if (interview.getInterviewType().equals("錄取") && user.getRating() == null) {
      rating = newRating;
      if (interviewResult.equals("缺席")) {
        user.setAbscence((user.getAbscence() + 1));
      } else if (interviewResult.equals("應約")) {
        user.setPresence(user.getPresence() + 1);
      }
      user.setRating(rating);
      userService.updateUser(user);
    } else if (interview.getInterviewType().equals("錄取")) {
      if (interviewResult.equals("缺席")) {
        user.setAbscence((user.getAbscence() + 1));
      } else if (interviewResult.equals("應約")) {
        user.setPresence(user.getPresence() + 1);
      }
      user.setRating((rating + newRating) / 2);
      userService.updateUser(user);
    }
    interviewService.updateInterviewResult(interviewResult, interviewId);
    return "redirect:/applicationNInterview";
  }
}