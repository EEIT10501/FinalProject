package com.funwork.controller;

import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.ResumeService;
import com.funwork.service.UserService;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostJobController {
  static final Logger logger = Logger.getLogger("com.funwork");
  private static final String LOGIN_USER = "loginUser";
  private static final String JOB_BEAN = "jobBean";
  private static final String TAIPEICITY = "taipeiCityNameJSON";
  private static final String NEWTAIPEICITY = "newTaipeiCityNameJSON";
  private static final String REDIRECT_MANAGE_JOB = "redirect:/manageJob";
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
  Job jobBean = null;

  /**
   * Get applictionList by job.
   */
  @GetMapping(value = "/applications")
  public String pullApplicantsByJob(@RequestParam("id") Integer id, Model model) {
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
    model.addAttribute("jobId", id);
    return "employerManage/applicantsList";
  }

  @GetMapping(value = "/jobProfile")
  public String getJobPostById(@RequestParam("id") Integer id, Model model) {
    model.addAttribute("job", jobService.getJobById(id));
    return "employerManage/jobProfile";
  }

  /**
   * Provide blank form for making job profile.
   */
  @GetMapping(value = "/addJobProfile")
  public String getRegisterCompanyForm(Model model, HttpSession session) {
    Job job = new Job();
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    List<String> companyNameList = companyService.findAllCompanyByUser(loginUser);
    String taipeiCityNameJson = jobService.getCityNameList("台北市");
    String newTaipeiCityNameJson = jobService.getCityNameList("新北市");
    model.addAttribute(JOB_BEAN, job);
    model.addAttribute(TAIPEICITY, taipeiCityNameJson);
    model.addAttribute(NEWTAIPEICITY, newTaipeiCityNameJson);
    model.addAttribute("companyNameList", companyNameList);
    return "employerManage/addJobProfile";
  }

  /**
   * Method below is for updating job post "編輯工作". Add additional evaluation on
   * whether job posting is active.
   */
  @GetMapping(value = "/modJobProfile")
  public String getRegisterCompanyForm(Model model, @RequestParam("jobId") Integer jobId, HttpSession session,
      String replicate) {
    Job job = jobService.getJobById(jobId);
    boolean isActive = job.getReviewStatus().equalsIgnoreCase("發布中");
    if (!isActive) {
      String addressCityArea = job.getAddress().substring(0, 3);
      String addressCityName = job.getAddress().substring(3, 6);
      String addressStreetNRest = job.getAddress().substring(6);
      job.setCityArea(addressCityArea);
      job.setCityName(addressCityName);
      job.setAddress(addressStreetNRest);
      model.addAttribute(JOB_BEAN, job);
      jobBean = job;
      User loginUser = (User) session.getAttribute(LOGIN_USER);
      List<String> companyNameList = companyService.findAllCompanyByUser(loginUser);
      String taipeiCityNameJson = jobService.getCityNameList("台北市");
      String newTaipeiCityNameJson = jobService.getCityNameList("新北市");
      model.addAttribute(TAIPEICITY, taipeiCityNameJson);
      model.addAttribute(NEWTAIPEICITY, newTaipeiCityNameJson);
      model.addAttribute("companyNameList", companyNameList);
      return "employerManage/modJobProfilePage";
    } else {
      model.addAttribute("error1", "發布中的工作無法修改");
      model.addAttribute("job", job);
      return "employerManage/jobProfile";
    }
  }

  /**
   * Process new Job.
   */
  @PostMapping(value = "/addJobProfile")
  public String processPostNewJob(@ModelAttribute("newJobPost") Job jbean, HttpSession session) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    jobService.insertJob(jbean, loginUser.getUserId());

    return REDIRECT_MANAGE_JOB;

  }

  /**
   * create a new job or to update one both enter same controller here. check
   * active job posting count against limit and then check if job posting is to
   * update previous record or insert new record
   */
  @PostMapping(value = "/modJobProfilePage")
  public String processPostNewJob(@ModelAttribute("jobBean") Job jbean, HttpSession session, Model model,
      String replicate) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    int activePost = jobService.getJobPostedCount(loginUser.getUserId());
    Integer limit = loginUser.getJobPostLimit();

    if (activePost <= limit) {
      if (jobBean == null) {
        jobService.insertJob(jbean, loginUser.getUserId());
        jobBean = null;
      } else {
        jobService.updateJobPostById(jobBean.getJobId(), jbean);
      }
    } else {
      if (jobBean.getJobId() != null) {
        jobService.updateJobPostById(jobBean.getJobId(), jbean);
        jobBean = null;
      } else {
        model.addAttribute("error2", "超出工作刊登上限額度");
        return "employerManage/addJobProfile";
      }
    }
    return REDIRECT_MANAGE_JOB;
  }

  /**
   * provide data-included jobBean to users when replicate is called.
   */
  @GetMapping(value = "/replicate")
  public String replicateGetExistingJob(@ModelAttribute("jobBean") Job jbean, @RequestParam("jobId") Integer jobId,
      Model model) {
    Job job = jobService.getJobById(jobId);
    String addressCityArea = job.getAddress().substring(0, 3);
    String addressCityName = job.getAddress().substring(3, 6);
    String addressStreetNRest = job.getAddress().substring(6);
    job.setCityArea(addressCityArea);
    job.setCityName(addressCityName);
    job.setAddress(addressStreetNRest);
    model.addAttribute(JOB_BEAN, job);
    jobBean = job;
    String taipeiCityNameJson = jobService.getCityNameList("台北市");
    String newTaipeiCityNameJson = jobService.getCityNameList("新北市");
    model.addAttribute(TAIPEICITY, taipeiCityNameJson);
    model.addAttribute(NEWTAIPEICITY, newTaipeiCityNameJson);

    return "employerManage/repNModJobProfilePage";
  }

  /**
   * receive data-modified jobBean from users after replicate is called and
   * edited.
   */
  @PostMapping(value = "/receivedUpdatedPost")
  public String replicateExistingJob(@ModelAttribute("jobBean") Job jbean, HttpSession session, Model model,
      RedirectAttributes redA) {
    User loginUser = (User) session.getAttribute(LOGIN_USER);
    int activePost = jobService.getJobPostedCount(loginUser.getUserId());
    Integer limit = loginUser.getJobPostLimit();

    if (activePost < limit) {
      jobService.insertJob(jbean, loginUser.getUserId());
      List<Job> jobsUpdated = jobService.findJobByUserId(loginUser.getUserId());
      model.addAttribute("jobs", jobsUpdated);
      return "employerManage/manageJobPage";
    } else {
      redA.addFlashAttribute("errorRep", "超出上限, 無法新增工作");
      return REDIRECT_MANAGE_JOB;
    }
  }

  /**
   * Query All Resume by Excel.
   */
  @GetMapping(value = "/resumes", produces = "application/vnd.ms-excel")
  public String queryAllResumesExcel(Model model, @RequestParam("jobId") Integer jobId) {
    List<Application> list = applicationService.findAllApplicantsByJob(jobService.getJobById(jobId));
    List<Resume> reslist = new LinkedList<>();
    for (Application app : list) {
      Resume resume = resumeService.getResumeByUserId(app.getUser().getUserId());
      reslist.add(resume);
    }
    model.addAttribute("allMembers", reslist);
    return "fileDownload/showMembers";
  }

  /**
   * Query All Resume by Excel.
   */
  @GetMapping(value = "resumesAAA/{key}.xls", produces = "application/vnd.ms-excel")
  public String displayMemberExcel(@PathVariable Integer key, Model model) {
    Resume resume = resumeService.getResumeByUserId(key);
    model.addAttribute(resume);
    return "fileDownload/showMember";
  }

  @GetMapping(value = "/getJobPostedCount/{userId}")
  @ResponseBody
  public Boolean isJobPostedExceed(@PathVariable("userId") Integer userId) {
    Boolean isJobPostedExceed = false;
    int nowPostCount = jobService.getJobPostedCount(userId);
    User user = userService.getUserById(userId);
    if (nowPostCount >= user.getJobPostLimit()) {
      isJobPostedExceed = true;
    }
    return isJobPostedExceed;
  }

  /**
   * Query All Resume by Pdf.
   */
  @GetMapping(value = "/resumes", produces = "application/pdf")
  public String queryAllResumesPdf(Model model, @RequestParam("jobId") Integer jobId) {
    List<Application> list = applicationService.findAllApplicantsByJob(jobService.getJobById(jobId));
    List<Resume> reslist = new LinkedList<>();
    for (Application app : list) {
      Resume resume = resumeService.getResumeByUserId(app.getUser().getUserId());
      reslist.add(resume);
    }
    model.addAttribute("allMembers", reslist);
    model.addAttribute("job", jobService.getJobById(jobId));
    return "fileDownload/showMembers";
  }

  @GetMapping(value = "/getAllJobPostingCount")
  @ResponseBody
  public Integer getAllJobPostingCount() {
    return jobService.getAllJobPostingCount();
  }
}