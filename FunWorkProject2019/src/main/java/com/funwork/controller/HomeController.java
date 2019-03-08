package com.funwork.controller;

import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

import com.funwork.model.Resume;
import com.funwork.service.MessageService;
import com.funwork.service.ResumeService;
import com.funwork.service.ScheuleService;

@Controller
public class HomeController {

	@Autowired
	MessageService messageService;
	@Autowired
	ScheuleService scheuleService;
	@Autowired
	ResumeService resumeService;
	@Autowired
	ServletContext context;

	public HomeController() {
	}

	@RequestMapping("/")
	public String Home() {
		return "index";
	}

	@RequestMapping("/form")
	public String Form() {
		return "pages/form";
	}

	
	@RequestMapping("/chat")
	public String Chat(Model model) {
		// 在這邊抓當前User的歷史訊息傳到訊息頁面
		model.addAttribute("oldMessage", "54321 : 嗨 ( 2019 年 2 月 23 日 )");
		return "pages/chat";
	}


	@RequestMapping(value = "/getPicture/{userId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletRequest resp, @PathVariable Integer userId) {


		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String fileName = "";
		int len = 0;
		Resume resume = resumeService.getResumeByUserId(userId);
		if (resume != null) {
			Blob blob = resume.getProfilePic();
			fileName = resume.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("HomeController的getPicture()發生SQLException:" + e.getMessage());
				}
			} else {
//				media = toByteArray(filePath);
//				fileName = filePath;
			}
		} else {
//			media = toByteArray(filePath);
//			fileName = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(fileName);
		MediaType mediaType = MediaType.valueOf(mimeType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

}
