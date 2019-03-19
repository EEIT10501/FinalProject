package com.funwork.dao;

import com.funwork.model.Application;
import com.funwork.model.Job;
import java.util.Date;
import java.util.List;

public interface ApplicationDao {
  Application findByPrimaryKey(int key);

  Application findByDate(Date date);

  void insertApplication(Integer userId, Integer jobId, String question);

  void saveApplication(Application application);

  void updateApplication(Application application);

  void deleteApplicationByPrimaryKey(int key);

  void deleteAllApplications();

  public boolean isApplicationExist(Application application);

  List<Application> getApplicationByUserId(Integer userId);

  void updateLatestMsg(Integer apId, String msg);

  List<Application> getApplicationByUserIdByTime(Integer userId);

  List<Application> findAllApplicantByJobId(Job job);

  void refuseUser(Integer apId);

  List<Application> findAllApplications(Integer userId);

  List<Application> findByJobId(Integer userId);

}
