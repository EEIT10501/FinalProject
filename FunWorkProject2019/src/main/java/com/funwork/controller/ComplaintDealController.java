package com.funwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Complaint;
import com.funwork.service.ComplaintService;

@Controller
public class ComplaintDealController {

	@Autowired
	ComplaintService complaintService;

	@RequestMapping(value = "/cpsDeal", method = RequestMethod.GET)
	public String complaintDealList(Model model) {
		List<Complaint> complaintList = complaintService.getComplaintDealList();
		model.addAttribute("complaintList", complaintList);
		return "cpdeal/cpsDeal";
	}

	@RequestMapping(value = "/cpDeal/{cpId}", method = RequestMethod.GET)
	public String complaintDeal(Model model, @PathVariable Integer cpId) {
		Complaint complaint = complaintService.getComplaintById(cpId);
		model.addAttribute("cpBean", complaint);
		return "cpdeal/cpDeal";
	}

	@RequestMapping(value = "/cpDeal/{cpId}", method = RequestMethod.POST)
	public String processComplaintDealForm(@PathVariable Integer cpId, @RequestParam(name = "isRemove") String isRemove,
			@RequestParam(name = "closeReason", required = false) String closeReason,
			@RequestParam(name = "jobId") Integer jobId) {
		complaintService.processComplaint(cpId, closeReason, isRemove, jobId);
		return "redirect:/cpsDeal";
	}

	@RequestMapping(value = "/cpApply", method = RequestMethod.POST)
	public String complaintApply(@RequestParam("type") String type, @RequestParam("content") String content,
			@RequestParam("jobId") Integer jobId) {
		complaintService.insertCp(type, content, jobId);
		return "redirect:/jobDetail/" + jobId;
	}

	@RequestMapping(value = "/cpsHistory", method = RequestMethod.GET)
	public String complaintHistoryList(Model model) {
		List<Complaint> complaintList = complaintService.getComplaintHistoryList();
		model.addAttribute("complaintList", complaintList);
		return "cpdeal/cpsDealHistory";
	}

	@RequestMapping(value = "/cpsHistory/{cpId}", method = RequestMethod.GET)
	public String complaintHistory(Model model, @PathVariable Integer cpId) {
		Complaint complaint = complaintService.getComplaintById(cpId);
		model.addAttribute("cpBean", complaint);
		return "cpdeal/cpDealHistory";
	}

}
