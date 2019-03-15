package com.funwork.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ComplaintDao;
import com.funwork.dao.JobDao;
import com.funwork.dao.NotificationDao;
import com.funwork.model.Complaint;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.service.ComplaintService;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintDao complaintDao;
	@Autowired
	JobDao jobDao;
	@Autowired
	NotificationDao notificationDao;

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintDao.getAllComplaints();
	}

	@Override
	public List<Complaint> getComplaintDealList() {
		return complaintDao.getComplaintDealList();
	}

	@Override
	public Complaint getComplaintById(Integer cpId) {
		return complaintDao.getComplaintById(cpId);
	}

	@Override
	public Complaint processComplaint(Integer cpId, String closeReason, String isRemove, Integer jobId) {
		Complaint cp = null;

		if (isRemove.equals("remove")) {
			closeReason = "因職缺違反規定，由系統管理員下架";
			cp = complaintDao.processComplaint(cpId, closeReason);
			closeReason = "因職缺違反相關規定(" + cp.getType() + ")，由系統管理員下架。";
			Job job = jobDao.jobRemove(jobId, closeReason);
			Notification notification = new Notification();
			notification.setContent("您的職缺(" + job.getTitle() + ")因違反規定(" + cp.getType() + ")，已由系統管理員下架");
			notification.setTime(new Timestamp(System.currentTimeMillis()));
			notification.setType(2);
			notification.setUser(job.getJobOwner());
			notificationDao.insertNotification(notification);
		} else if (isRemove.equals("close")) {
			cp = complaintDao.processComplaint(cpId, closeReason);
		}
		return cp;
	}

	@Override
	public void insertCp(String type, String content, Integer jobId) {
		Complaint cp = new Complaint();
		cp.setContent(content);
		cp.setJob(jobDao.getJobById(jobId));
		cp.setStatus("待處理");
		cp.setSubmitTime(new Timestamp(System.currentTimeMillis()));
		cp.setType(type);
		complaintDao.insertCp(cp);
	}

	@Override
	public List<Complaint> getComplaintHistoryList() {
		return complaintDao.getComplaintHistoryList();
	}

}
