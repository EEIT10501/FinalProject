package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ComplaintDao;
import com.funwork.model.Complaint;
import com.funwork.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	ComplaintDao dao;

	public ComplaintServiceImpl() {
	}

	@Transactional
	@Override
	public List<Complaint> getAllComplaints() {
		return dao.getAllComplaints();
	}

	@Transactional
	@Override
	public List<Complaint> getComplaintDealList() {
		return dao.getComplaintDealList();
	}

	@Transactional
	@Override
	public Complaint getComplaintById(Integer cpId) {
		return dao.getComplaintById(cpId);
	}

	@Transactional
	@Override
	public Complaint processComplaint(Integer cpId, String closeReason) {
		return dao.processComplaint(cpId, closeReason);
	}

	@Transactional
	@Override
	public void insertCp(Complaint cp) {
		dao.insertCp(cp);
	}

}
