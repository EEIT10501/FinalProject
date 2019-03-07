package com.funwork.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.funwork.exception.CompanyNotFoundException;
import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;

@Controller
public class EmployerController {

	@Autowired
	CompanyService companyService;

	@Autowired
	JobService jobService;

	@Autowired
	ServletContext context;

	@RequestMapping("/employerPortal")
	public String accessCompanyMain() {
		return "employerPortal";
	}

	@RequestMapping("/pages/indexTest")
	public String login() {
		return "pages/indexTest";
	}

	@RequestMapping("/manageJob")
	public String manageJob(Model model) {
		List<Job> list = jobService.getAllJobs();
		model.addAttribute("jobs", list);
		return "manageJobPage";
	}

	@RequestMapping("/manageCompanyPage")
	public String list(Model model) {
		List<Company> list = companyService.findAllCompanys();
		model.addAttribute("companys", list);
		return "manageCompanyPage";
	}

	@RequestMapping("/jobManCond")
	public String jobManCond(Model model) {
		List<Company> list = companyService.findAllCompanys();
		model.addAttribute("companys", list);
		return "test";
	}

	@RequestMapping(value = "/searchResultByReviewStatus", method = RequestMethod.POST)
	public String getcompanysByReviewStatus(@RequestParam("filterCompanys") String status, Model model) {
		List<Company> companys = companyService.findAllCompanys(status);
		model.addAttribute("companys", companys);
		return "manageCompanyPage";
	}

	@RequestMapping("/company")
	public String getcompanyById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("company", companyService.findByPrimaryKey(id));
		return "companyProfile";
	}

	@RequestMapping(value = "/postJob", method = RequestMethod.GET)
	public String getAddNewcompanyForm(Model model) {
		Job jb = new Job();
		model.addAttribute("jobBean", jb);
		return "addJob";
	}

	@RequestMapping(value = "/registerCompany", method = RequestMethod.GET)
	public String getRegisterCompanyForm(Model model) {
		Company cb = new Company();
		cb.setName("中天");
		cb.setTaxId("12345678");
		cb.setAddress("Taipei");
		model.addAttribute("companyBean", cb);
		return "registerCompany";
	}

	@RequestMapping(value = "/registerCompany", method = RequestMethod.POST)
	public String processgetAddNewcompanyForm(@ModelAttribute("companyBean") Company cb, BindingResult result,
			HttpServletRequest request) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位：" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		System.out.println(cb.getName());
		System.out.println(cb.getTaxId());
		System.out.println(cb.getAddress());

		MultipartFile companyLicensure = cb.getcompanyLicensureImage();
		String originalFilename = companyLicensure.getOriginalFilename();
		cb.setFileName(originalFilename);

//		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
//		String rootDirectory = context.getRealPath("/");

		if (companyLicensure != null && !companyLicensure.isEmpty()) {
			try {
				byte[] b = companyLicensure.getBytes();
				Blob blob = new SerialBlob(b);
				cb.setLicensure(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常:  " + e.getMessage());
			}
		}
		companyService.saveCompany(cb);

//		File imageFolder = new File(rootDirectory, "images");
//		if (!imageFolder.exists()) {
//			imageFolder.mkdirs();
//			File file = new File(imageFolder, cb.getCompanyId() + ext);
//			try {
//				companyLicensure.transferTo(file);
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		return "redirect:/companys";
	}

	@ExceptionHandler(CompanyNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, CompanyNotFoundException exception) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("invalidCompanyId", exception.getCompanyId());
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mv.setViewName("companyNotFound");
		return mv;
	}

	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("name", "taxId", "address", "companyLicensureImage");
	}
}
