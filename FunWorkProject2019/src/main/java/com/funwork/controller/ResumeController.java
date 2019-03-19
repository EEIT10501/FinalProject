package com.funwork.controller;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.funwork.model.Experience;
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
	
	public ResumeController() {

	}

	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public String getaddResume(Model model) {
		Resume rb = new Resume();
		model.addAttribute("Resume", rb);
		return "/resume";
	}
    //用<form:form>傳回整個Bean物件,BindingResult result 要傳圖片必須要有;
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public String getaddResume(@ModelAttribute("Resume") Resume rb, BindingResult result, 
	HttpServletRequest req) {
		//建立MultipartFile loginImage容器來存放檔案名稱
		MultipartFile loginImage = rb.getProfilePart();
		String originalFilename = loginImage.getOriginalFilename();
		rb.setFileName(originalFilename);
		
		if (loginImage != null && !loginImage.isEmpty()) {
			try {
				byte[] b = loginImage.getBytes();
				Blob blob = new SerialBlob(b);
				rb.setProfilePic(blob);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:  " + e.getMessage());
			}
		}	
		//不用form:intput,直接用input取得工作經驗,並判斷當表格有無輸入時如何INSET值;
		String[] positions = req.getParameterValues("position");
		String[] companys = req.getParameterValues("company");
		String[] terms = req.getParameterValues("term");
		
		for(int i=0  ;i < positions.length ;i++) {
	
			if( positions[i] != null && companys[i] != null && terms[i] !=null) {				
				Experience experience = new Experience();
				experience.setPosition(positions[i]);	
				experience.setCompany(companys[i]);
				experience.setTerm(terms[i]);
				experienceService.insertExperience(experience);
			}else if( positions[i] == null||companys[i] != null||terms[i] !=null) {
				Experience experience = new Experience();
				experience.setCompany(companys[i]);
				experience.setTerm(terms[i]);
				experienceService.insertExperience(experience);
			
			}else if( positions[i] != null||companys[i] == null||terms[i] !=null) {
				Experience experience = new Experience();
				experience.setPosition(positions[i]);	
				experience.setTerm(terms[i]);
				experienceService.insertExperience(experience);				

			}else if( positions[i] != null||companys[i] != null||terms[i] ==null) {
				Experience experience = new Experience();
				experience.setPosition(positions[i]);
				experience.setCompany(companys[i]);
				experienceService.insertExperience(experience);				
			}else if( positions[i] != null||companys[i] == null||terms[i] ==null) {
				Experience experience = new Experience();
				experience.setPosition(positions[i]);	
				experienceService.insertExperience(experience);			
			
			}else if( positions[i] == null||companys[i] != null||terms[i] ==null) {
				Experience experience = new Experience();
				experience.setCompany(companys[i]);	
				experienceService.insertExperience(experience);			
	
			}else if( positions[i] == null || companys[i] == null || terms[i] !=null) {
				Experience experience = new Experience();
				experience.setTerm(terms[i]);	
				experienceService.insertExperience(experience);			
			}else {
				
				break;
			}
}	
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("loginUser");
		resumeService.addResume(rb,user.getUserId());
        

		
		
		
		return "redirect:/resume";

	}
}
