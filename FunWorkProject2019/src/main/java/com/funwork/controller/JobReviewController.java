package com.funwork.controller;

import com.funwork.model.Job;
import com.funwork.service.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobReviewController {

  @Autowired
  JobService jobService;

  /**
   * Return job status = '待審核'.
   */
  @GetMapping(value = "/jobsReview")
  public String getJobListForReview(Model model) {
    List<Job> jobList = jobService.getJobReviewList();
    model.addAttribute("jobList", jobList);
    return "jobreview/jobsReview";
  }

  /**
   * Return job detail.
   */
  @GetMapping(value = "/jobReview/{jobId}")
  public String getJobDetail(Model model, @PathVariable Integer jobId) {
    Job job = jobService.getJobById(jobId);
    model.addAttribute("jobBean", job);
    return "jobreview/jobReview";
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
    return "jobreview/jobsReviewHistory";
  }

  /**
   * Return job review history detail.
   */
  @GetMapping(value = "/jobReviewHistory/{jobId}")
  public String getJobReviewHistory(Model model, @PathVariable Integer jobId) {
    Job job = jobService.getJobById(jobId);
    model.addAttribute("jobBean", job);
    return "jobreview/jobReviewHistory";
  }
}
