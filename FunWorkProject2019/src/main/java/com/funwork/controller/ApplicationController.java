package com.funwork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.funwork.model.Application;
import com.funwork.model.Company;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.InterviewService;
import com.funwork.service.UserService;

@Controller
@RequestMapping
public class ApplicationController {

	@Autowired
	InterviewService interviewService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	UserService userService;
	@Autowired
	ServletContext context;

	@RequestMapping(value="/manageApplications")
	public String pullApplicantsByUser(Model model, HttpServletRequest request) {
		System.out.println("enter manageApplications");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		List<Application> list;
		if (user!=null) {
			list = applicationService.findAllApplications(user.getUserId());
			model.addAttribute("applications", list);
			return "application/manageApplicationPage";
		}else {
			return "redirect:/";
		}
	}
	
	
}
