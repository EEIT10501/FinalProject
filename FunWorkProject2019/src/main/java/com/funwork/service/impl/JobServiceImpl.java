package com.funwork.service.impl;

import com.funwork.dao.CityDao;
import com.funwork.dao.CompanyDao;
import com.funwork.dao.JobDao;
import com.funwork.dao.NotificationDao;
import com.funwork.dao.UserDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.model.User;
import com.funwork.service.JobService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JobServiceImpl implements JobService {

  @Autowired
  JobDao jobDao;
  @Autowired
  CityDao cityDao;
  @Autowired
  CompanyDao companyDao;
  @Autowired
  NotificationDao notificationDao;
  @Autowired
  UserDao userDao;

  @Override
  public List<Job> getAllJobs() {
    return jobDao.getAllJobs();
  }

  @Override
  public List<Job> getJobReviewList() {
    return jobDao.getJobReviewList();
  }

  @Override
  public List<Job> getJobPassed() {
    return jobDao.getJobPassed();
  }

  @Override
  public List<Job> getJobByCityName(Integer cityId) {
    return jobDao.getJobByCityName(cityId);
  }

  @Override
  public List<Job> getJobByCityArea(Integer cityId) {
    return jobDao.getJobByCityArea(cityId);
  }

  @Override
  public Job getJobById(Integer jobId) {
    return jobDao.getJobById(jobId);
  }

  @Override
  public Job jobReviewPass(Integer jobId) {

    Job job = jobDao.getJobById(jobId);
    job.setReviewStatus("發布中");
    job.setReviewTime(new Timestamp(System.currentTimeMillis()));
    jobDao.updateJob(job);

    Notification notification = new Notification();
    notification.setContent("您的職缺(" + job.getTitle() + ")已通過審核");
    notification.setTime(new Timestamp(System.currentTimeMillis()));
    notification.setType(2);
    notification.setUser(job.getJobOwner());
    notificationDao.insertNotification(notification);
    return job;
  }

  @Override
  public Job jobReviewFail(Integer jobId, String failReason) {
    
    Job job = jobDao.getJobById(jobId);
    job.setReviewStatus("審核失敗");
    job.setReviewTime(new Timestamp(System.currentTimeMillis()));
    job.setFailReason(failReason);
    jobDao.updateJob(job);
    
    Notification notification = new Notification();
    notification.setContent("您的職缺(" + job.getTitle() + ")審核失敗");
    notification.setTime(new Timestamp(System.currentTimeMillis()));
    notification.setType(2);
    notification.setUser(job.getJobOwner());
    notificationDao.insertNotification(notification);
    return job;
  }

  @Override
  public List<City> getAllCitys() {
    return cityDao.getAllCitys();

  }

  @Override
  public List<City> getCityName(Integer cityId) {
    return cityDao.getCityName(cityId);

  }

  @Override
  public List<Job> findJobByUserId(Integer userId) {
    return jobDao.findJobByUserId(userId);
  }

  @Override
  public List<Job> findJobByUserIdNJobStatus(Integer userId) {
    return jobDao.findJobByUserIdNJobStatus(userId);
  }

  @Override
  public List<Job> getCorrectJobs() {
    return jobDao.getCorrectJobs();
  }

  @Override
  public List<Job> getReviewHistory() {
    return jobDao.getReviewHistory();
  }

  @Override
  public List<String> getCityAreaList() {
    return cityDao.getCityAreaList();
  }

  @Override
  public String getCityNameList(String cityArea) {
    return cityDao.getCityNameList(cityArea);
  }

  @Override
  public City getCityByCityName(String cityName) {
    return cityDao.getCityByCityName(cityName);
  }

  @Override
  public Job insertJob(Job job, Integer userId) {
    User jobOwner = userDao.findByPrimaryKey(userId);
    String cityName = job.getCityName();
    job.setAddress(job.getCityArea() + cityName + job.getAddress());
    job.setIsExposure(false);
    job.setIsFilled(false);
    job.setReviewStatus("待審核");
    job.setSubmitTime(new Timestamp(System.currentTimeMillis()));
    job.setViewTimes(0);
    job.setJobOwner(jobOwner);
    String companyName = job.getCompanyName();
    if (!companyName.equals("-1")) {
      job.setJobCompany(companyDao.findCompanyByUserAndName(userId, companyName));
    }
    job.setCity(cityDao.getCityByCityName(cityName));

    return jobDao.insertJob(job);
  }

  @Override
  public int getJobPostedCount(Integer userId) {
    return jobDao.getJobPostedCount(userId);
  }

}
