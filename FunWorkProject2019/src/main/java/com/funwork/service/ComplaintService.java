package com.funwork.service;

import java.util.List;

import com.funwork.model.Complaint;

public interface ComplaintService {
	List<Complaint> getAllComplaints();

	List<Complaint> getComplaintDealList();

	Complaint getComplaintById(Integer cpId);
	
	Complaint processComplaint(Integer cpId, String closeReason);
}
