package com.funwork.controller;

import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.model.User;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmployerController {
  static final Logger logger = Logger.getLogger("com.funwork");
  private static final String LOGIN_USER = "loginUser";
  private static final String REDIRECT_TO_INDEX = "redirect:/";
  private static final String COMPANYS = "companys";
  @Autowired
  CompanyService companyService;
  @Autowired
  JobService jobService;
  @Autowired
  ServletContext context;

  @GetMapping("/mainHub")
  public String accessMain() {
    return "employerManage/mainHub";
  }

  /**
   * Show jobs by user.
   */
  @GetMapping("/manageJob")
  public String manageJob(Model model, HttpSession session) {
    User user = (User) session.getAttribute(LOGIN_USER);
    if (user != null) {
      List<Job> list = jobService.findJobByUserId(user.getUserId());
      model.addAttribute("jobs", list);
      return "employerManage/manageJobPage";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Show companys by user.
   */
  @GetMapping("/manageCompanyPage")
  public String list(Model model, HttpSession session) {
    User user = (User) session.getAttribute(LOGIN_USER);
    if (user != null) {
      List<Company> list = companyService.findAllCompanyByUserId(user.getUserId());
      model.addAttribute(COMPANYS, list);
      return "employerManage/manageCompanyPage";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  /**
   * Get company page by companyId.
   */
  @GetMapping("/company")
  public String getcompanyById(@RequestParam("id") Integer id, Model model) {
    Company company = companyService.findByPrimaryKey(id);
    List<Job> list = jobService.findJobByUserId(company.getUser().getUserId());
    model.addAttribute("company", company);
    model.addAttribute("jobs", list);
    return "employerManage/companyProfile";
  }

  /**
   * Let user register company.
   */
  @GetMapping(value = "/registerCompany")
  public String getRegisterCompanyForm(Model model) {
    Company cb = new Company();
    model.addAttribute("companyBean", cb);
    return "employerManage/registerCompany";
  }

  /**
   * Process company register.
   */
  @PostMapping(value = "/registerCompany")
  public String processgetAddNewcompanyForm(@ModelAttribute("companyBean") Company cb, 
      HttpServletRequest request) {
    MultipartFile image = cb.getCompanyLicensureImage();
    String originalFilename = image.getOriginalFilename();
    cb.setFileName(originalFilename);

    if (companyService.isTaxIdExist(cb.getTaxId())) {
      request.setAttribute("taxIdExist", "已存在相同統一編號的公司");
      return "employerManage/registerCompany";
    }

    if (!image.isEmpty()) {
      try {
        byte[] b = image.getBytes();
        Blob blob = new SerialBlob(b);
        cb.setLicensure(blob);
      } catch (Exception e) {
        logger.warning(e.getMessage());
      }
    }

    HttpSession session = request.getSession();
    User user = (User) session.getAttribute(LOGIN_USER);
    if (user != null) {
      cb.setUser(user);
      companyService.saveCompany(cb);
      return "redirect:/manageCompanyPage";
    } else {
      return REDIRECT_TO_INDEX;
    }
  }

  @GetMapping(value = "/addCorpProfile/{companyId}")
  public String getAddCorpProfileForm(@PathVariable("companyId") Integer id, Model model) {
    model.addAttribute("companyBean", companyService.findByPrimaryKey(id));
    return "employerManage/addCorpProfile";
  }

  /**
   * Update company page.
   */
  @PostMapping(value = "/addCorpProfile/{companyId}")
  public String processAddCorpProfileForm(@ModelAttribute("companyBean") Company cb,
      @PathVariable("companyId") Integer id) {
    MultipartFile logoImage = cb.getCompanyLogo();
    MultipartFile coverImage = cb.getCompanyCoverPic();

    if (logoImage != null && !logoImage.isEmpty() && coverImage != null && !coverImage.isEmpty()) {
      try {
        byte[] b = logoImage.getBytes();
        Blob blob = new SerialBlob(b);
        cb.setLogo(blob);
        byte[] b2 = coverImage.getBytes();
        Blob blob2 = new SerialBlob(b2);
        cb.setCoverPic(blob2);
      } catch (Exception e) {
        logger.warning(e.getMessage());
      }
    }
    companyService.updateCompanyById(id, cb);
    return "redirect:/company?id=" + id;
  }

  /**
   * Get LicPicture by companuId.
   */
  @GetMapping(value = "/getLicPicture/{companyId}")
  public ResponseEntity<byte[]> getLicPicture(HttpServletResponse resp, 
      @PathVariable Integer companyId) {

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      Blob blob = bean.getLicensure();
      filename = bean.getFileName();
      if (blob != null) {
        try {
          len = (int) blob.length();
          media = blob.getBytes(1, len);
        } catch (SQLException e) {
          logger.warning(e.getMessage());
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
    String mimeType = context.getMimeType(filename);
    MediaType mediatype = MediaType.valueOf(mimeType);
    headers.setContentType(mediatype);
    return new ResponseEntity<>(media, headers, HttpStatus.OK);
  }

  /**
   * Get CoverPicture by companuId.
   */
  @GetMapping(value = "/getCoverPicture/{companyId}")
  public ResponseEntity<byte[]> getCoverPicture(HttpServletResponse resp, 
      @PathVariable Integer companyId) {

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      Blob blob = bean.getCoverPic();
      filename = bean.getFileName();
      if (blob != null) {
        try {
          len = (int) blob.length();
          media = blob.getBytes(1, len);
        } catch (SQLException e) {
          logger.warning(e.getMessage());
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
    String mimeType = context.getMimeType(filename);
    MediaType mediatype = MediaType.valueOf(mimeType);
    headers.setContentType(mediatype);
    return new ResponseEntity<>(media, headers, HttpStatus.OK);
  }

  /**
   * Get LogoPicture by companuId.
   */
  @GetMapping(value = "/getLogoPicture/{companyId}")
  public ResponseEntity<byte[]> getLogoPicture(HttpServletResponse resp, 
      @PathVariable Integer companyId) {

    String filePath = "/resources/images/NoImage.jpg";
    byte[] media = null;
    HttpHeaders headers = new HttpHeaders();
    String filename = "";
    int len = 0;
    Company bean = companyService.findByPrimaryKey(companyId);

    if (bean != null) {
      Blob blob = bean.getLogo();
      filename = bean.getFileName();
      if (blob != null) {
        try {
          len = (int) blob.length();
          media = blob.getBytes(1, len);
        } catch (SQLException e) {
          logger.warning(e.getMessage());
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
    String mimeType = context.getMimeType(filename);
    MediaType mediatype = MediaType.valueOf(mimeType);
    headers.setContentType(mediatype);
    return new ResponseEntity<>(media, headers, HttpStatus.OK);
  }

  private byte[] toByteArray(String filePath) {
    String root = context.getRealPath("/");
    root = root.substring(0, root.length() - 1);
    String fileLocation = root + filePath;
    byte[] b = null;
    int currentBytesRead = 0;
    int totalBytesRead = 0;
    try {
      java.io.File file = new java.io.File(fileLocation);
      long size = file.length();
      b = new byte[(int) size];
      InputStream fis = context.getResourceAsStream(filePath);
      while ((currentBytesRead = fis.read(b)) > 0) {
        totalBytesRead += currentBytesRead;
      }
      logger.log(Level.ALL, "totalBytesRead:{0}", totalBytesRead);
    } catch (IOException e) {
      logger.warning(e.getMessage());
    }
    return b;
  }
}