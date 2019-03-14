package com.funwork.dao;

import java.util.List;

import com.funwork.model.Complaint;

public interface ComplaintDao {
	List<Complaint> getAllComplaints();

	List<Complaint> getComplaintDealList();

	Complaint getComplaintById(Integer cpId);

	Complaint processComplaint(Integer cpId, String closeReason);
	
	void insertCp(Complaint cp);
	
	List<Complaint> getComplaintHistoryList();
}
