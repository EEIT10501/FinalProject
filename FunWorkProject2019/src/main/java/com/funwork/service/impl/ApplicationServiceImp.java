package com.funwork.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.service.ApplicationService;

@Transactional
@Service
public class ApplicationServiceImp implements ApplicationService {
	@Autowired
	ApplicationDao dao;

	@Transactional
	@Override
	public Application findByPrimaryKey(int key) {
		return dao.findByPrimaryKey(key);
	}

	@Transactional
	@Override
	public Application findByDate(Date date) {
		return dao.findByDate(date);
	}

	@Transactional
	@Override
	public void insertApplication(Integer userId, Integer jobId, String question) {
		dao.insertApplication(userId, jobId, question);
	}

	@Transactional
	@Override
	public void saveApplication(Application application) {
		dao.saveApplication(application);
	}

	@Transactional
	@Override
	public void updateApplication(Application application) {
		dao.updateApplication(application);
	}

	@Transactional
	@Override
	public void deleteApplicationByPrimaryKey(int key) {
		dao.deleteAllApplications();
	}

	@Transactional
	@Override
	public List<Application> findAllApplicantsByJob(Job job) {
		return dao.findAllApplicantByJobId(job);
	}

	@Transactional
	@Override
	public void deleteAllApplications() {
		dao.deleteAllApplications();
	}

	@Transactional
	@Override
	public boolean isApplicationExist(Application application) {
		return dao.isApplicationExist(application);
	}

	@Transactional
	@Override
	public List<Application> getApplicationByUserId(Integer userId) {
		return dao.getApplicationByUserId(userId);
	}

	@Transactional
	@Override
	public void updateLatestMsg(Integer apId, String msg) {
		dao.updateLatestMsg(apId, msg);
	}
	
	@Transactional
	@Override
	public List<Application> getApplicationByUserIdByTime(Integer userId) {
		return dao.getApplicationByUserIdByTime(userId);
	}
	

	@Transactional
	@Override
	public List<Application> findAllApplications(Integer userId) {
		return dao.findAllApplications(userId);
	}

	@Transactional
	@Override
	public void refuseUser(Integer apId) {
		dao.refuseUser(apId);
	}
	@Transactional
	@Override
	public List<Application> findByJobId(Integer jobId) {
		return dao.findByJobId(jobId);
	}

  @Override
  public Application isApplicantExist(Integer userId, Integer jobId) {
    return dao.isApplicantExist(userId,jobId);
  }

}
