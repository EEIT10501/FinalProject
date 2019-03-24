package com.funwork.controller;

import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ResumeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResumeController {
  @Autowired
  ResumeService resumeService;

  /**
   * Let users insert or update their resume.
   */
  @GetMapping(value = "/resume")
  public String processResume(Model model, HttpServletRequest req) {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("loginUser");
    Resume resume = resumeService.getResumeByUserId(user.getUserId());
    if (resume == null) {
      resume = new Resume();
    }
    model.addAttribute("resume", resume);
    return "/resume";
  }

  /**
   * Process insert or update resume.
   */
  @PostMapping(value = "/resume")
  public String processResume(@ModelAttribute("resume") Resume resume, HttpServletRequest req) {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("loginUser");
    Integer userId = user.getUserId();
    resumeService.addResume(resume, userId);
    return "redirect:/resume";
  }
}