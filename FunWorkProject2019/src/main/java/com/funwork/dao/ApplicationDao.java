package com.funwork.dao;

import java.util.Date;
import java.util.List;

import com.funwork.model.Application;
import com.funwork.model.Job;

public interface ApplicationDao {
	Application findByPrimaryKey(int key);

	Application findByDate(Date date);

	void insertApplication(Integer userId, Integer jobId, String question);

	void saveApplication(Application Application);

	void updateApplication(Application Application);

	void deleteApplicationByPrimaryKey(int key);

	void deleteAllApplications();

	public boolean isApplicationExist(Application Application);

	List<Application> getApplicationByUserId(Integer userId);

	void updateLatestMsg(Integer apId, String msg);

	List<Application> findAllApplicantByJobId(Job job);
	
	void refuseUser(Integer apId);

}
