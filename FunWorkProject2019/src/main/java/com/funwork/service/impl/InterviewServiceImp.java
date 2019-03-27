package com.funwork.service.impl;

import com.funwork.dao.ApplicationDao;
import com.funwork.dao.InterviewDao;
import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.service.InterviewService;
import com.funwork.service.JobService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InterviewServiceImp implements InterviewService {
  @Autowired
  InterviewDao interviewDao;
  @Autowired
  ApplicationDao applicationDao;
  @Autowired
  JobService jobDao;

  @Override
  public Interview findByPrimaryKey(int key) {
    return interviewDao.findByPrimaryKey(key);
  }

  @Override
  public Integer saveInterview(String interType, String interComment, 
      String interPlace, String interTime, Integer apId) {
    Interview interview = new Interview();
    interview.setInterviewType(interType);
    interview.setInterviewComment(interComment);
    interview.setInterviewPlace(interPlace);
    interview.setInterviewTime(Timestamp.valueOf(interTime.replace("T", " ") + ":00"));
    Application ap = applicationDao.findByPrimaryKey(apId);
    interview.setApplication(ap);
    interview.setInterviewStatus("待回應");
    interviewDao.saveInterview(interview);
    return ap.getJob().getJobId();
  }

  @Override
  public void updateInterviewResult(String interviewResult, int key) {
    Interview interview = interviewDao.findByPrimaryKey(key);
    interview.setInterviewResult(interviewResult);
    interviewDao.updateInterview(interview);
  }

  public List<Interview> findByApplicationIdAndTimeProcessing(int key) {
    return interviewDao.findByApplicationIdAndTimeProcessing(key);
  }

  @Override
  public List<Interview> findByApplicationIdAndTimeCompleted(int key) {
    return interviewDao.findByApplicationIdAndTimeCompleted(key);
  }

  @Override
  public List<Interview> findByApplicationIdAndTimeExpired(int key) {
    return interviewDao.findByApplicationIdAndTimeExpired(key);
  }

  @Override
  public List<Interview> findInterviewByAdmit(Integer jobId) {
    return interviewDao.findInterviewByAdmit(jobId);
  }

  @Override
  public Interview findByAdmitJobUserName(Integer jobId, String userName) {
    return interviewDao.findByAdmitJobUserName(jobId, userName);
  }

  @Override
  public List<Interview> findInterviewsByJobOwner(Integer userId) {
    return interviewDao.findInterviewsByJobOwner(userId);
  }

  @Override
  public void updateInterview(Interview interview) {
    interviewDao.updateInterview(interview);
  }
}