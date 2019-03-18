package com.funwork.dao;

import com.funwork.model.Job;
import java.util.List;

public interface JobDao {

  List<Job> getAllJobs();

  List<Job> getJobReviewList();

  List<Job> getJobPassed();

  List<Job> getJobByCityName(Integer cityId);

  List<Job> getJobByCityArea(Integer cityId);

  Job getJobById(Integer jobId);

  Job updateJob(Job job);

  List<Job> getCorrectJobs();

  List<Job> getReviewHistory();

  List<Job> findJobByUserId(Integer userId);

  List<Job> findJobByUserIdNJobStatus(Integer userId);

  Job insertJob(Job job);

  int getJobPostedCount(Integer userId);

}