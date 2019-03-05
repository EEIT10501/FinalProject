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

}
