package com.funwork.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

//	@RequestMapping("/postJob")
//	public String jobPost(Model model) {
//		List<Company> list = service.findAllCompanys();
//		model.addAttribute("companys", list);
//		return "addJob";
//	}
//	@RequestMapping("/update/stock")
//	public String updateStock(Model model) {
//		service.updateAllStock();
//		return "redirect:/companys";
//	}
//
//	@RequestMapping("/queryByCategory")
//	public String getAllCategoryList(Model model) {
//		List<String> list = service.getAllCategories();
//		model.addAttribute("categoryList", list);
//		return "types/category";
//	}
//
//	@RequestMapping("/companys/{category}")
//	public String getcompanysByCategory(@PathVariable("category") String category, Model model) {
//		List<BookBean> companys = service.getcompanysByCategory(category);
//		model.addAttribute("companys", companys);
//		return "companys";
//	}
//
//	@RequestMapping("/company")
//	public String getcompanyById(@RequestParam("id") Integer id, Model model) {
//		model.addAttribute("company", service.getcompanyById(id));
//		return "company";
//	}
//
	@RequestMapping(value = "/postJob", method = RequestMethod.GET)
	public String getAddNewcompanyForm(Model model) {
		Job jb = new Job();
		model.addAttribute("jobBean", jb);
		return "addJob";
	}

	@RequestMapping(value = "/registerCompany", method = RequestMethod.GET)
	public String getRegisterCompanyForm(Model model) {
		Company cb = new Company();
		model.addAttribute("companyBean", cb);
		return "registerCompany";
	}

	@RequestMapping(value = "/registerCompany", method = RequestMethod.POST)
	public String processgetAddNewcompanyForm(@ModelAttribute("companyBean") Company cb, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位：" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		System.out.println(cb.getAddress());

		MultipartFile companyLicensure = cb.getcompanylicensure();
		String originalFilename = companyLicensure.getOriginalFilename();
		cb.setFileName(originalFilename);

		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = context.getRealPath("/");

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

		File imageFolder = new File(rootDirectory, "images");
		if (!imageFolder.exists()) {
			imageFolder.mkdirs();
			File file = new File(imageFolder, cb.getCompanyId() + ext);
			try {
				companyLicensure.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:/companys";
	}

//
//	@RequestMapping(value = "/getPicture/{bookId}", method = RequestMethod.GET)
//	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer bookId) {
//
//		System.out.println("Enter controller");
//
//		String filePath = "/resources/images/NoImage.jpg";
//		byte[] media = null;
//		HttpHeaders headers = new HttpHeaders();
//		String filename = "";
//		int len = 0;
//		BookBean bean = service.getcompanyById(bookId);
//
////		System.out.println(bean.);
//		if (bean != null) {
//			Blob blob = bean.getCoverImage();
//			filename = bean.getFileName();
//			if (blob != null) {
//				try {
//					len = (int) blob.length();
//					media = blob.getBytes(1, len);
//				} catch (SQLException e) {
//					e.printStackTrace();
//					throw new RuntimeException("companyController的getPicture()發生SQLException: " + e.getMessage());
//				}
//			} else {
//				media = toByteArray(filePath);
//				filename = filePath;
//			}
//		} else {
//			media = toByteArray(filePath);
//			filename = filePath;
//		}
//		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//		System.out.println(filename);
//		String mimeType = context.getMimeType(filename);
//
//		MediaType mediatype = MediaType.valueOf(mimeType);
//		System.out.println("mediaType: " + mediatype);
//		headers.setContentType(mediatype);
//		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
//		return responseEntity;
//	}
//	
	@ExceptionHandler(CompanyNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, CompanyNotFoundException exception) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("invalidCompanyId", exception.getCompanyId());
		mv.addObject("exception", exception);
		mv.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mv.setViewName("companyNotFound");
		return mv;
	}

//
//	private byte[] toByteArray(String filePath) {
//		String root = context.getRealPath("/");
//		root = root.substring(0, root.length()-1);
//		String fileLocation = root + filePath;
//		byte[] b = null;
//		try {
//			java.io.File file = new java.io.File(fileLocation);
//			long size = file.length();
//			b = new byte[(int) size];
//			InputStream fis = context.getResourceAsStream(filePath);
//			fis.read(b);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return b;
//	}
//
//	@ModelAttribute("companyList")
//	public Map<Integer, String> getCompanyList() {
//		Map<Integer, String> companyMap = new HashMap<>();
//		List<Company> list = companyService.findAllCompanys();
//		for (Company cb : list) {
//			companyMap.put(cb.getCompanyId(), cb.getName());
//		}
//		return companyMap;
//	}
//
//	@ModelAttribute("categoryList")
//	public List<String> getCategoryList() {
//		return service.getAllCategories();
//	}
//
	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("author", "bookNo", "category", "price", "title", "companyId");
	}
}
