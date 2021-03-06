package com.funwork.controller;

import com.funwork.model.Company;
import com.funwork.model.Job;
import com.funwork.model.Order;
import com.funwork.model.User;
import com.funwork.service.CompanyService;
import com.funwork.service.JobService;
import com.funwork.service.OrderService;
import com.funwork.service.UserService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

  @Autowired
  JobService jobService;
  @Autowired
  CompanyService companyService;
  @Autowired
  OrderService orderService;
  @Autowired
  UserService userService;

  /**
   * Return job status = '待審核'.
   */
  @GetMapping(value = "/jobsReview")
  public String getJobListForReview(Model model) {
    List<Job> jobList = jobService.getJobReviewList();
    model.addAttribute("jobList", jobList);
    return "review/jobsReview";
  }

  /**
   * Return job detail.
   */
  @GetMapping(value = "/jobReview/{jobId}")
  public String getJobDetail(Model model, @PathVariable Integer jobId) {
    Job job = jobService.getJobById(jobId);
    model.addAttribute("jobBean", job);
    return "review/jobReview";
  }

  /**
   * Call service base on review result.
   */
  @PostMapping(value = "/jobReview/{jobId}")
  public String processJobReview(@PathVariable Integer jobId, 
      @RequestParam(name = "isPass") String isPass,
      @RequestParam(name = "failReason", required = false) String failReason) {
    if (isPass.equals("pass")) {
      jobService.jobReviewPass(jobId);
    } else if (isPass.equals("fail")) {
      jobService.jobReviewFail(jobId, failReason);
    }
    return "redirect:/jobsReview";
  }

  /**
   * Return jobs review history.
   */
  @GetMapping(value = "/jobsReviewHistory")
  public String getJobsReviewHistory(Model model) {
    List<Job> jobList = jobService.getReviewHistory();
    model.addAttribute("jobList", jobList);
    return "review/jobsReviewHistory";
  }

  /**
   * Return job review history detail.
   */
  @GetMapping(value = "/jobReviewHistory/{jobId}")
  public String getJobReviewHistory(Model model, @PathVariable Integer jobId) {
    Job job = jobService.getJobById(jobId);
    model.addAttribute("jobBean", job);
    return "review/jobReviewHistory";
  }

  /**
   * Return company status = '審查中'.
   */
  @GetMapping(value = "/companysReview")
  public String getCompanyListForReview(Model model) {
    List<Company> companyList = companyService.getCompanyReviewList();
    model.addAttribute("companyList", companyList);
    return "review/companysReview";
  }
  
  /**
   * Return company detail.
   */
  @GetMapping(value = "/companyReview/{companyId}")
  public String getCompanyDetail(Model model, @PathVariable Integer companyId) {
    Company company = companyService.findByPrimaryKey(companyId);
    model.addAttribute("companyBean", company);
    return "review/companyReview";
  }
  
  /**
   * Call service base on review result.
   */
  @PostMapping(value = "/companyReview/{companyId}")
  public String processCompanyReview(@PathVariable Integer companyId, 
      @RequestParam(name = "isPass") String isPass,
      @RequestParam(name = "failReason", required = false) String failReason) {
    if (isPass.equals("pass")) {
      companyService.companyReviewPass(companyId);
    } else if (isPass.equals("fail")) {
      companyService.companyReviewPassReviewFail(companyId, failReason);
    }
    return "redirect:/companysReview";
  }
  
  /**
   * Return company review history.
   */
  @GetMapping(value = "/companysReviewHistory")
  public String getCompanysReviewHistory(Model model) {
    List<Company> companyList = companyService.getReviewHistory();
    model.addAttribute("companyList", companyList);
    return "review/companysReviewHistory";
  }

  /**
   * Return company review history detail.
   */
  @GetMapping(value = "/companyReviewHistory/{companyId}")
  public String getCompanyReviewHistory(Model model, @PathVariable Integer companyId) {
    Company company = companyService.findByPrimaryKey(companyId);
    model.addAttribute("companyBean", company);
    return "review/companyReviewHistory";
  }
  
  /**
   * Return order history list.
   */
  @GetMapping(value = "/orderList")
  public String getOrderHistoryList(Model model) {
    List<Order> orderlist = orderService.getAllOrders();
    model.addAttribute("orderlist", orderlist);
    return "review/ordersHistory";
  }
  
  @GetMapping(value = "/queryMember")
  public String queryMember() {
    return "review/queryMember";
  }
  
  /**
   * get user detail by email.
   */
  @PostMapping(value = "/queryMember")
  public String queryMemberProcess(Model model,@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    model.addAttribute("user", user);
    return "review/memberDetail";
  }
}
