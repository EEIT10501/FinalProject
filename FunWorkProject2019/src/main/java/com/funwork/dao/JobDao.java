package com.funwork.dao;

import java.util.List;

import com.funwork.model.Job;

public interface JobDao {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();
	
	List<Job> getJobPassed();
	
	List<Job> getJobByCity(Integer cityId);

	Job getJobById(Integer jobId);

	void jobReviewPass(Integer jobId);

	void jobReviewFail(Integer jobId, String failReason);

}