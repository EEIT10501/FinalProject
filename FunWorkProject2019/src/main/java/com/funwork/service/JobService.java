package com.funwork.service;

import java.util.List;

import com.funwork.model.Job;

public interface JobService {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();

	Job getJobById(Integer jobId);

	Job jobReviewPass(Integer jobId);

	Job jobReviewFail(Integer jobId, String failReason);
	
	Job jobRemove(Integer jobId, String removeReason);
}