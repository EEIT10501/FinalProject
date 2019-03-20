package com.funwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ExperienceService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;

@Controller
public class ResumeController {

  @Autowired
  UserService userService;

  @Autowired
  ResumeService resumeService;

  @Autowired
  ExperienceService experienceService;

  @GetMapping(value = "/resume")
  public String getAddResume(Model model,HttpServletRequest req) {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("loginUser");
    Resume rb = resumeService.getResumeByUserId(user.getUserId());
    if (rb == null) {
      rb = new Resume();
    }
    model.addAttribute("resume",rb);
    return "/resume";
  }

  @PostMapping(value = "/resume")
  public String getAddResume(@ModelAttribute("resume") Resume resume, HttpServletRequest req) {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("loginUser");
//    String[] positions = req.getParameterValues("position");
//    String[] companys = req.getParameterValues("company");
//    String[] terms = req.getParameterValues("term");
    resumeService.addResume(resume, user.getUserId());
    return "redirect:/resume";
  }
}