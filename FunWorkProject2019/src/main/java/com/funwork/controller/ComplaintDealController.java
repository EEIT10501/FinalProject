package com.funwork.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.funwork.model.Complaint;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.service.ComplaintService;
import com.funwork.service.JobService;
import com.funwork.service.NotificationService;

@Controller
public class ComplaintDealController {

	@Autowired
	ComplaintService complaintService;
	@Autowired
	JobService jobService;
	@Autowired
	NotificationService notificationService;

	@RequestMapping(value = "/cpsDeal", method = RequestMethod.GET)
	public String complaintDealList(Model model) {
		List<Complaint> list = complaintService.getComplaintDealList();
		model.addAttribute("complaintList", list);
		return "cpdeal/cpsDeal";
	}

	@RequestMapping(value = "/cpDeal/{cpId}", method = RequestMethod.GET)
	public String reviewJobForm(Model model, @PathVariable Integer cpId) {
		Complaint complaint = complaintService.getComplaintById(cpId);
		model.addAttribute("cpBean", complaint);
		return "cpdeal/cpDeal";
	}

	@RequestMapping(value = "/cpDeal/{cpId}", method = RequestMethod.POST)
	public String processComplaintDealForm(@PathVariable Integer cpId, @RequestParam(name = "isRemove") String isRemove,
			@RequestParam(name = "closeReason", required = false) String closeReason,
			@RequestParam(name = "jobId") Integer jobId) {

		if (isRemove.equals("remove")) {
			Complaint cp = complaintService.processComplaint(cpId, closeReason);
			closeReason = "因職缺違反相關規定(" + cp.getType() + ")，由系統管理員下架";
			Job job = jobService.jobRemove(jobId, closeReason);
			Notification notification = new Notification();
			notification.setContent("您的職缺(" + job.getTitle() + ")因違反規定(" + cp.getType() + ")，已由系統管理員下架");
			notification.setTime(new Timestamp(System.currentTimeMillis()));
			notification.setType(2);
			notification.setUser(job.getJobOwner());
			notificationService.insertNotification(notification);
		} else if (isRemove.equals("close")) {
			complaintService.processComplaint(cpId, closeReason);
		}
		return "redirect:/cpsDeal";
	}

	@RequestMapping(value = "/cpApply", method = RequestMethod.POST)
	public String complaintApply(@RequestParam("type") String type, @RequestParam("content") String content,
			@RequestParam("jobId") Integer jobId) {

		Complaint cp = new Complaint();
		cp.setContent(content);
		cp.setJob(jobService.getJobById(jobId));
		cp.setStatus("待處理");
		cp.setSubmitTime(new Timestamp(System.currentTimeMillis()));
		cp.setType(type);
		complaintService.insertCp(cp);
		
		return "redirect:/jobDetail/" + jobId;
	}
}
