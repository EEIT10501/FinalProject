package com.funwork.dao;

import java.util.List;

import com.funwork.model.Job;

public interface JobDao {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();

	Job getJobById(Integer jobId);

}