package com.funwork.service;

import com.funwork.model.City;
import com.funwork.model.Job;
import java.util.List;
import java.util.Map;

public interface JobService {
  List<Job> getJobReviewList();

  List<Job> getJobByCityName(Integer cityId);

  List<Job> getJobByCityArea(Integer cityId);

  Job getJobById(Integer jobId);

  Job jobReviewPass(Integer jobId);

  Job jobReviewFail(Integer jobId, String failReason);

  List<City> getCityName(Integer cityId);

  List<Job> findJobByUserId(Integer userId);

  List<Job> getCorrectJobs();

  List<Job> getReviewHistory();

  String getCityNameList(String cityArea);

  void insertJob(Job job, Integer userId);

  int getJobPostedCount(Integer userId);

  Map<String, String> getGeocoderLatitude(String address);

  void updateJobPostById(Integer jobId, Job jbean);

  List<Job> getJobsBySearchStr(String searchStr);

  void changeJobExposure(Integer jobId);

  void changeJobFilled(Integer jobId);

  City getCityByPk(Integer cityId);

  Integer getJobExposureCount(Integer userId);

  Integer getAllJobPostingCount();

  String getAllPostingJobTypeJson();

  void updateJobByExpired();

  Integer updateViewTimesByJob(Integer jobId);
}