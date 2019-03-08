package com.funwork.service;

import java.util.Date;
import java.util.List;

import com.funwork.model.Application;

public interface ApplicationService {
	Application findByPrimaryKey(int key);

	Application findByDate(Date date);
	
	void insertApplication(Integer userId, Integer jobId);

	void saveApplication(Application Application);

	void updateApplication(Application Application);

	void deleteApplicationByPrimaryKey(int key);

	List<Application> findAllApplications();

	void deleteAllApplications();

	public boolean isApplicationExist(Application Application);

}
