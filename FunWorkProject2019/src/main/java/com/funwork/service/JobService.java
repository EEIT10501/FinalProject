package com.funwork.service;

import java.util.List;

import com.funwork.model.Job;

public interface JobService {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();

	Job getJobById(Integer jobId);

	void jobReviewPass(Integer jobId);

	void jobReviewFail(Integer jobId, String failReason);
}