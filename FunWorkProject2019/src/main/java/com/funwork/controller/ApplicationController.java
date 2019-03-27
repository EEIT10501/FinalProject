package com.funwork.controller;

import com.funwork.model.Interview;
import com.funwork.model.User;
import com.funwork.service.InterviewService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ApplicationController {
  @Autowired
  InterviewService interviewService;

  /**
   * get interviewList by JobOwner.
   */
  @GetMapping(value = "/applicationNInterview")
  public String awaitResponseInterview(Model model, HttpSession session) {
    User user = (User) session.getAttribute("loginUser");
    List<Interview> interviewsPerJobOwner = interviewService
        .findInterviewsByJobOwner(user.getUserId());
    model.addAttribute("interviewsPerJobOwner", interviewsPerJobOwner);
    return "application/applicationNInterview";
  }
}