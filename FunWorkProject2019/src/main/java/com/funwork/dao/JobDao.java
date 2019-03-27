package com.funwork.dao;

import com.funwork.model.Job;
import java.util.List;

public interface JobDao {
  List<Job> getJobReviewList();

  List<Job> getJobByCityName(Integer cityId);

  List<Job> getJobByCityArea(Integer cityId);

  Job getJobById(Integer jobId);

  Job updateJob(Job job);

  List<Job> getCorrectJobs();

  List<Job> getReviewHistory();

  List<Job> findJobByUserId(Integer userId);

  void insertJob(Job job);

  int getJobPostedCount(Integer userId);

  List<Job> getJobsBySearchStr(String searchStr);

  Integer getJobExposureCount(Integer userId);

  Integer getAllJobPostingCount();

  String getAllPostingJobTypeJson();

  void updateJobByExpired();
}