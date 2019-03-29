package com.funwork.controller;

import com.funwork.model.Complaint;
import com.funwork.service.ComplaintService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComplaintProcessController {
  @Autowired
  ComplaintService complaintService;

  /**
   * Return status = '待審核' 的申訴紀錄.
   */
  @GetMapping(value = "/cpsProcess")
  public String getComplaintListForProcess(Model model) {
    List<Complaint> complaintList = complaintService.getComplaintProcessList();
    model.addAttribute("complaintList", complaintList);
    return "cpprocess/cpsProcess";
  }

  /**
   * Return Pk = cpId 的申訴紀錄.
   */
  @GetMapping(value = "/cpProcess/{cpId}")
  public String getComplaintDetail(Model model, @PathVariable Integer cpId) {
    Complaint complaint = complaintService.getComplaintById(cpId);
    model.addAttribute("cpBean", complaint);
    return "cpprocess/cpProcess";
  }

  @PostMapping(value = "/cpProcess/{cpId}")
  public String processComplaint(@PathVariable Integer cpId, 
      @RequestParam(name = "isRemove") String isRemove,
      @RequestParam(name = "closeReason", required = false) String closeReason,
      @RequestParam(name = "jobId") Integer jobId) {
    complaintService.processComplaint(cpId, closeReason, isRemove, jobId);
    return "redirect:/cpsProcess";
  }

  @PostMapping(value = "/cpApply")
  public String applyComplaint(@RequestParam("type") String type, 
      @RequestParam("content") String content,
      @RequestParam("jobId") Integer jobId) {
    complaintService.insertCp(type, content, jobId);
    return "redirect:/jobDetail/" + jobId;
  }

  /**
   * Return申訴歷史紀錄.
   */
  @GetMapping(value = "/cpsHistory")
  public String getComplaintHistoryList(Model model) {
    List<Complaint> complaintList = complaintService.getComplaintHistoryList();
    model.addAttribute("complaintList", complaintList);
    return "cpprocess/cpsProcessHistory";
  }

  /**
   * Return Pk = cpId 的申訴歷史紀錄.
   */
  @GetMapping(value = "/cpsHistory/{cpId}")
  public String getComplaintHistoryDetail(Model model, @PathVariable Integer cpId) {
    Complaint complaint = complaintService.getComplaintById(cpId);
    model.addAttribute("cpBean", complaint);
    return "cpprocess/cpProcessHistory";
  }
}