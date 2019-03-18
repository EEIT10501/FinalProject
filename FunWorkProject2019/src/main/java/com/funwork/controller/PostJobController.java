package com.funwork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;

@Controller
@RequestMapping
public class PostJobController {

	@Autowired
	CompanyService companyService;

	@Autowired
	JobService jobService;

	@Autowired
	UserService userService;

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	ResumeService resumeService;

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/jobProfile")
	public String getJobPostById(@RequestParam("id") Integer id, Model model) {
		System.out.println("job selected: " + id);
		model.addAttribute("job", jobService.getJobById(id));
		return "employerManage/jobProfile";
	}

	@RequestMapping(value = "/applications")
	public String pullApplicantsByJob(@RequestParam("id") Integer id, Model model) {
		System.out.println("ready to pull applicant by jobId" + id);
		List<Application> list = applicationService.findAllApplicantsByJob(jobService.getJobById(id));
		List<Resume> reslist = new LinkedList<>();
		LinkedList<User> userlist = new LinkedList<>();
		for (Application app : list) {
			Resume resume = resumeService.getResumeByUserId(app.getUser().getUserId());
			User user = userService.getUserById(app.getUser().getUserId());
			reslist.add(resume);
			userlist.add(user);
		}
		model.addAttribute("applicantsByJob", list);
		model.addAttribute("resumes", reslist);
		model.addAttribute("users", userlist);
		return "employerManage/applicantsList";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.GET)
	public String getRegisterCompanyForm(Model model, HttpServletRequest request) {
		Job jbean = new Job();
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		List<String> companyNameList = companyService.findAllCompanyByUser(loginUser);
		String taipeiCityNameJSON = jobService.getCityNameList("台北市");
		String newTaipeiCityNameJSON = jobService.getCityNameList("新北市");

		model.addAttribute("newJobPost", jbean);
		model.addAttribute("taipeiCityNameJSON", taipeiCityNameJSON);
		model.addAttribute("newTaipeiCityNameJSON", newTaipeiCityNameJSON);
		model.addAttribute("companyNameList", companyNameList);

		return "employerManage/addJobProfile";
	}

	@RequestMapping(value = "/addJobProfile", method = RequestMethod.POST)
	public String processPostNewJob(@ModelAttribute("newJobPost") Job jbean, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		jobService.insertJob(jbean, loginUser.getUserId());

		return "redirect:/manageJob";
	}
	@RequestMapping(value = "/getProfilePic/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getLogoPicture(HttpServletResponse resp, @PathVariable Integer userId) {

		System.out.println("UserId"+userId);
		System.out.println("Enter Profile getPicture");

		String filePath = "/resources/images/NoImage.jpg";
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
//		Company bean = companyService.findByPrimaryKey(companyId);
		Resume bean = resumeService.getResumeByUserId(userId);
		
		
		if (bean != null) {
			System.out.println("enter if");
			Blob blob = bean.getProfilePic();
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
	@RequestMapping(value = "/getJobPostedCount/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Integer getJobPostedCount(@PathVariable("userId") Integer userId) {
		Integer count = jobService.getJobPostedCount(userId);
		return count;
	}

}
