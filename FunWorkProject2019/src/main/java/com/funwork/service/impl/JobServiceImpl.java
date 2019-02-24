package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.JobDao;
import com.funwork.model.Job;
import com.funwork.service.JobService;


@Service
public class JobServiceImpl implements JobService {
	@Autowired
	JobDao dao;

	public JobServiceImpl() {
	}
	
	@Override
	@Transactional
	public List<Job> getAllJobs() {
		return dao.getAllJobs();
	}

}
