package com.funwork.dao;

import java.util.List;

import com.funwork.model.Application;
import com.funwork.model.Interview;

public interface InterviewDao {
	Interview findByPrimaryKey(int key);
	
	void saveInterview(Interview interview);

	void updateInterview(Interview interview);

	void deleteInterviewByPrimaryKey(int key);

	List<Interview> findAllInterviews();

	void deleteAllInterviews();

	public boolean isInterviewExist(Interview interview);

	List<Interview> findByApplicationIds(int key);

	List<Interview> findByApplicationIdAndTime(int key);

}
