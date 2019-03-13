package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.CityDao;
import com.funwork.dao.JobDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.User;
import com.funwork.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	JobDao dao;
	
	@Autowired
	CityDao citydao;

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
	public List<Job> getJobByCityName(Integer cityId) {
		return dao.getJobByCityName(cityId);
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
	
	@Transactional
	@Override
	public List<City> getAllCitys(){
		return citydao.getAllCitys();
		
	}
	
	@Transactional
	@Override
	public List<City> getCityName(Integer cityId){
		return citydao.getCityName(cityId);
				
	}
	@Transactional
	@Override
	public List<Job> findJobByUserId(Integer userId) {
		return dao.findJobByUserId(userId);
	}
	
	@Transactional
	@Override
	public List<Job> findJobByUserIdNJobStatus(Integer userId){
		return dao.findJobByUserIdNJobStatus(userId);
	}
	
	@Transactional
	@Override
	public List<Job> getCorrectJobs(){		
		return dao.getCorrectJobs();
	}

}
