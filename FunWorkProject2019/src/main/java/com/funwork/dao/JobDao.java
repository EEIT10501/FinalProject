package com.funwork.dao;

import java.util.List;

import com.funwork.model.Job;

public interface JobDao {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();
	
	List<Job> getJobPassed();
	
	List<Job> getJobByCityName(Integer cityId);
	
	List<Job> getJobByCityArea(Integer cityId);

	Job getJobById(Integer jobId);

	Job jobReviewPass(Integer jobId);

	Job jobReviewFail(Integer jobId, String failReason);

	Job jobRemove(Integer jobId, String removeReason);

}