package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Complaint;
import com.funwork.service.ComplaintService;

@Controller
public class ComplaintDealController {

  @Autowired
  ComplaintService complaintService;

  @GetMapping(value = "/cpsDeal")
  public String complaintDealList(Model model) {
    List<Complaint> complaintList = complaintService.getComplaintDealList();
    model.addAttribute("complaintList", complaintList);
    return "cpdeal/cpsDeal";
  }

  @GetMapping(value = "/cpDeal/{cpId}")
  public String complaintDeal(Model model, @PathVariable Integer cpId) {
    Complaint complaint = complaintService.getComplaintById(cpId);
    model.addAttribute("cpBean", complaint);
    return "cpdeal/cpDeal";
  }

  @PostMapping(value = "/cpDeal/{cpId}")
  public String processComplaintDealForm(@PathVariable Integer cpId, @RequestParam(name = "isRemove") String isRemove,
      @RequestParam(name = "closeReason", required = false) String closeReason,
      @RequestParam(name = "jobId") Integer jobId) {
    complaintService.processComplaint(cpId, closeReason, isRemove, jobId);
    return "redirect:/cpsDeal";
  }

  @PostMapping(value = "/cpApply")
  public String complaintApply(@RequestParam("type") String type, @RequestParam("content") String content,
      @RequestParam("jobId") Integer jobId) {
    complaintService.insertCp(type, content, jobId);
    return "redirect:/jobDetail/" + jobId;
  }

  @GetMapping(value = "/cpsHistory")
  public String complaintHistoryList(Model model) {
    List<Complaint> complaintList = complaintService.getComplaintHistoryList();
    model.addAttribute("complaintList", complaintList);
    return "cpdeal/cpsDealHistory";
  }

  @GetMapping(value = "/cpsHistory/{cpId}")
  public String complaintHistory(Model model, @PathVariable Integer cpId) {
    Complaint complaint = complaintService.getComplaintById(cpId);
    model.addAttribute("cpBean", complaint);
    return "cpdeal/cpDealHistory";
  }

}
