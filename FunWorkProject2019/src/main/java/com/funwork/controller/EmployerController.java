package com.funwork.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.funwork.exception.CompanyNotFoundException;
import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.model.User;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.UserService;

@Controller
public class EmployerController {

  @Autowired
  CompanyService companyService;

  @Autowired
  JobService jobService;

  @Autowired
  UserService userService;

  @Autowired
  ServletContext context;

  // 沒用到就刪掉
  @RequestMapping("/employerPortal")
  public String accessCompanyMain() {
    return "employerManage/employerPortal";
  }

  // 沒用到就刪掉
  @RequestMapping("/mainHub")
  public String accessMain() {
    return "employerManage/mainHub";
  }

  @RequestMapping("/addJobProfile")
  public String buildCorpProfile() {
    return "employerManage/addJobProfile";
  }

  // 這個路徑進去就掛掉
  @RequestMapping("/addCorpProfile")
  public String addCorpProfile() {
    return "employerManage/addCorpProfile";
  }

  @RequestMapping("/manageJob")
  public String manageJob(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("loginUser");

    if (user != null) {
      List<Job> list = jobService.findJobByUserId(user.getUserId());
      model.addAttribute("jobs", list);
      return "employerManage/manageJobPage";
    } else {
      return "redirect:/";
    }
  }

  @RequestMapping("/manageCompanyPage")
  public String list(Model model, HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("loginUser");
    if (user != null) {
      List<Company> list = companyService.findAllCompanyByUserId(user.getUserId());
      model.addAttribute("companys", list);
      return "employerManage/manageCompanyPage";
    } else {
      return "redirect:/";
    }
  }

  @ResponseBody
  @RequestMapping(value = "/searchResultByReviewStatus")
  public String getcompanysByReviewStatus(@RequestParam("qstr") String status, Model model) {
    System.out.println("received AJAX request and qstr is " + status);
    System.out.println("new request model content before service called: " + model.containsAttribute("companys"));
    List<Company> companys = companyService.findAllCompanys(status);
    System.out.println("companys list contains element(T:Empty): " + companys.isEmpty());
    System.out.println("list element number is " + companys.size());
    for (Company c : companys) {
      System.out.println(c.toString());
    }
    model.addAttribute("companys", companys);
    model.addAttribute("flag", companys);
//		return "redirect:/employerManage/manageCompanyPage";
//		return "redirect:employerManage/manageCompanyPage";
//		return "redirect:/manageCompanyPage";
//		return "employerManage/manageCompanyPage" + "";
    return "OK";
  }

  @RequestMapping(value = "/resultCorStatsJSON/{qstr}", method = RequestMethod.GET, produces = { "application/json" })
  public ResponseEntity<List<Company>> getcompanysByReviewStatus(Model model, @PathVariable("qstr") String qstr,
      HttpServletRequest request) {
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("loginUser");
    System.out.println(user.getUserId());
    System.out.println("received AJAX request and qstr is " + qstr);
    List<Company> list = companyService.findAllCompanys(qstr);
    List<Company> arr = new ArrayList<>();
    if (qstr.equalsIgnoreCase("全部")) {
      List<Company> listAll = companyService.findAllCompanyByUserId(user.getUserId());
      ResponseEntity<List<Company>> re = new ResponseEntity<>(listAll, HttpStatus.OK);
      return re;
    } else {
      for (Company com : list) {
        if (com.getUser().getUserId() == user.getUserId()) {
          arr.add(com);
        }
      }
      ResponseEntity<List<Company>> re = new ResponseEntity<>(arr, HttpStatus.OK);
      return re;
    }
  }

  @RequestMapping("/company")
  public String getcompanyById(@RequestParam("id") Integer id, Model model) {
    System.out.println("getCompanyById");
    model.addAttribute("company", companyService.findByPrimaryKey(id));
    return "employerManage/companyProfile";
  }

  @RequestMapping(value = "/registerCompany", method = RequestMethod.GET)
  public String getRegisterCompanyForm(Model model) {
    Company cb = new Company();
    model.addAttribute("companyBean", cb);
    return "employerManage/registerCompany";
  }

  @RequestMapping(value = "/registerCompany", method = RequestMethod.POST)
  public String processgetAddNewcompanyForm(@ModelAttribute("companyBean") Company cb, BindingResult result,
      HttpServletRequest request) {
    System.out.println("Enter controller");
    String[] suppressedFields = result.getSuppressedFields();
    if (suppressedFields.length > 0) {
      throw new RuntimeException("嘗試傳入不允許的欄位：" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
    }

    MultipartFile image = cb.getCompanyLicensureImage();
    String originalFilename = image.getOriginalFilename();
    cb.setFileName(originalFilename);
    String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
    String rootDirectory = context.getRealPath("/");

    if (image != null && !image.isEmpty()) {
      try {
        byte[] b = image.getBytes();
        Blob blob = new SerialBlob(b);
        cb.setLicensure(blob);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("檔案上傳發生異常:  " + e.getMessage());
      }
    }

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("loginUser");
    if (user != null) {
      cb.setUser(user);
      companyService.saveCompany(cb);
      return "redirect:/manageCompanyPage";
    } else {
      return "redirect:/";
    }
  }

  @RequestMapping(value = "/addCorpProfile", method = RequestMethod.GET)
  public String getAddCorpProfileForm(@RequestParam("id") Integer id, Model model) {
    System.out.println("here");
    model.addAttribute("companyBean", companyService.findByPrimaryKey(id));
    return "employerManage/addCorpProfile";
  }

  @RequestMapping(value = "/addCorpProfile", method = RequestMethod.POST)
  public String processAddCorpProfileForm(@ModelAttribute("companyBean") Company cb, BindingResult result,
      HttpServletRequest request) {
    System.out.println("Enter controller of processAddCorpProfileForm");
    String[] suppressedFields = result.getSuppressedFields();
    if (suppressedFields.length > 0) {
      throw new RuntimeException("嘗試傳入不允許的欄位：" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
    }
    MultipartFile logoImage = cb.getCompanyLogo();
    MultipartFile coverImage = cb.getCompanyCoverPic();
    System.out.println("coverImage: " + coverImage);
    String originalFilenameCover = coverImage.getOriginalFilename();

    if (logoImage != null && !logoImage.isEmpty() && coverImage != null && !coverImage.isEmpty()) {
      try {
        byte[] b = logoImage.getBytes();
        Blob blob = new SerialBlob(b);
        cb.setLogo(blob);
        byte[] b2 = coverImage.getBytes();
        Blob blob2 = new SerialBlob(b2);
        cb.setCoverPic(blob2);
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("檔案上傳發生異常:  " + e.getMessage());
      }
    }
    Integer id = cb.getCompanyId();
    companyService.updateCompanyById(id, cb);
    return "redirect:/company?id=" + id;
  }

  @RequestMapping(value = "/getLicPicture/{companyId}", method = RequestMethod.GET)
  public ResponseEntity<byte[]> getLicPicture(HttpServletResponse resp, @PathVariable Integer companyId) {

    System.out.println(companyId);
    System.out.println("Enter license getPicture");

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      System.out.println("enter if");
      Blob blob = bean.getLicensure();
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

  @RequestMapping(value = "/getCoverPicture/{companyId}", method = RequestMethod.GET)
  public ResponseEntity<byte[]> getCoverPicture(HttpServletResponse resp, @PathVariable Integer companyId) {

    System.out.println("Enter getCoverPicture controller");

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      System.out.println("enter if");
      Blob blob = bean.getCoverPic();
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

  @RequestMapping(value = "/getLogoPicture/{companyId}", method = RequestMethod.GET)
  public ResponseEntity<byte[]> getLogoPicture(HttpServletResponse resp, @PathVariable Integer companyId) {

    System.out.println(companyId);
    System.out.println("Enter logo getPicture");

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      System.out.println("enter if");
      Blob blob = bean.getLogo();
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

  @ExceptionHandler(CompanyNotFoundException.class)
  public ModelAndView handleError(HttpServletRequest request, CompanyNotFoundException exception) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("invalidCompanyId", exception.getCompanyId());
    mv.addObject("exception", exception);
    mv.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
    mv.setViewName("companyNotFound");
    return mv;
  }

}
