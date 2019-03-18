package com.funwork.service;

import java.util.Date;
import java.util.List;

import com.funwork.model.Application;
import com.funwork.model.Job;

public interface ApplicationService {
	Application findByPrimaryKey(int key);

	Application findByDate(Date date);
	
	void insertApplication(Integer userId, Integer jobId,String question);

	void saveApplication(Application application);

	void updateApplication(Application application);

	void deleteApplicationByPrimaryKey(int key);

	void deleteAllApplications();

	public boolean isApplicationExist(Application application);
	
	List<Application> getApplicationByUserId(Integer userId);
	
	void updateLatestMsg(Integer apId, String msg);


	List<Application> getApplicationByUserIdByTime(Integer userId);

	List<Application> findAllApplicantsByJob(Job job);
	
	void refuseUser(Integer apId);


	public List<Application> findAllApplications(Integer userId);

}
