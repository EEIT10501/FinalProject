package com.funwork.service;

import java.util.List;

import com.funwork.model.Application;
import com.funwork.model.Interview;

public interface JobSeekerService {

	List<Application> getApplicationByUserId(Integer userId);
	
	Interview findByPrimaryKey(int key);

}