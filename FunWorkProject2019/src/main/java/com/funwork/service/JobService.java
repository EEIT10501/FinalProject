package com.funwork.service;

import java.util.List;

import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.User;

public interface JobService {

	List<Job> getAllJobs();

	List<Job> getJobReviewList();
	
	List<Job> getJobPassed();
	
	List<Job> getJobByCityName(Integer cityId);
	
	List<Job> getJobByCityArea(Integer cityId);

	Job getJobById(Integer jobId);

	Job jobReviewPass(Integer jobId);

	Job jobReviewFail(Integer jobId, String failReason);
	
	Job jobRemove(Integer jobId, String removeReason);
	
	List<City> getAllCitys();
	
	List<City> getCityName(Integer cityId);

	List<Job> findJobByUserId(Integer userId);

	List<Job> findJobByUserIdNJobStatus(Integer userId);
}