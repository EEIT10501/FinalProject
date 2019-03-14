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
	
	@RequestMapping(value = "/getUserProfilePic/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getUserProfilePic(HttpServletResponse resp, @PathVariable Integer userId) {

		System.out.println(userId);
		System.out.println("Enter logo getPicture");

		String filePath = "/resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;

		User bean = userService.findByPrimaryKey(userId);
		
		if (bean != null) {
			System.out.println("enter if");
			Blob blob = bean.
			filename = bean.getFileName();
			if (blob != null) {
				System.out.println("enter blob != null");
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		System.out.println(filename);
		String mimeType = context.getMimeType(filename);

		MediaType mediatype = MediaType.valueOf(mimeType);
		System.out.println("mediaType: " + mediatype);
		headers.setContentType(mediatype);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	private byte[] toByteArray(String filePath) {
		String root = context.getRealPath("/");
		root = root.substring(0, root.length() - 1);
		String fileLocation = root + filePath;
		byte[] b = null;
		try {
			java.io.File file = new java.io.File(fileLocation);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filePath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

}
