package com.funwork.dao;

import com.funwork.model.Application;
import com.funwork.model.Job;
import java.util.List;

public interface ApplicationDao {
  Application findByPrimaryKey(int key);

  void insertApplication(Integer userId, Integer jobId, String question);

  void updateApplication(Application application);

  List<Application> getApplicationByUserId(Integer userId);

  void updateLatestMsg(Integer apId, String msg);

  List<Application> getApplicationByUserIdByTime(Integer userId);

  List<Application> findAllApplicantByJobId(Job job);

  void refuseUser(Integer apId);

  List<Application> findByJobId(Integer userId);

  Application isApplicantExist(Integer userId, Integer jobId);
}