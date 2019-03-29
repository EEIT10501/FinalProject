package com.funwork.service.impl;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.service.ApplicationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ApplicationServiceImp implements ApplicationService {
  @Autowired
  ApplicationDao dao;

  @Override
  public Application findByPrimaryKey(int key) {
    return dao.findByPrimaryKey(key);
  }

  @Override
  public void insertApplication(Integer userId, Integer jobId, String question) {
    dao.insertApplication(userId, jobId, question);
  }

  @Override
  public void updateApplication(Application application) {
    dao.updateApplication(application);
  }

  @Override
  public List<Application> findAllApplicantsByJob(Job job) {
    return dao.findAllApplicantByJobId(job);
  }

  @Override
  public List<Application> getApplicationByUserId(Integer userId) {
    return dao.getApplicationByUserId(userId);
  }

  @Override
  public void updateLatestMsg(Integer apId, String msg) {
    dao.updateLatestMsg(apId, msg);
  }

  @Override
  public List<Application> getApplicationByUserIdByTime(Integer userId) {
    return dao.getApplicationByUserIdByTime(userId);
  }

  @Override
  public void refuseUser(Integer apId) {
    dao.refuseUser(apId);
  }

  @Override
  public List<Application> findByJobId(Integer jobId) {
    return dao.findByJobId(jobId);
  }

  @Override
  public Application isApplicantExist(Integer userId, Integer jobId) {
    return dao.isApplicantExist(userId, jobId);
  }
}