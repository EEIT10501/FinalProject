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

	@Override
	@Transactional
	public List<Job> getJobReviewList() {
		return dao.getJobReviewList();
	}
	
	@Override
	@Transactional
	public List<Job> getJobPassed() {
		return dao.getJobPassed();
	}
	
	@Override
	@Transactional
	public List<Job> getJobByCity(Integer cityId) {
		return dao.getJobByCity(cityId);
	}
	
	@Transactional
	@Override
	public List<Job> getJobByCityArea(Integer cityId) {
		return dao.getJobByCityArea(cityId);
	}

	@Override
	@Transactional
	public Job getJobById(Integer jobId) {
		return dao.getJobById(jobId);
	}

	@Override
	@Transactional
	public Job jobReviewPass(Integer jobId) {
		return dao.jobReviewPass(jobId);
	}

	@Override
	@Transactional
	public Job jobReviewFail(Integer jobId, String failReason) {
		return dao.jobReviewFail(jobId, failReason);
	}

	@Transactional
	@Override
	public Job jobRemove(Integer jobId, String removeReason) {
		return dao.jobRemove(jobId, removeReason);
	}

}
