package com.funwork.service;

import java.util.List;

import com.funwork.model.Interview;

public interface InterviewService {

	Interview findByPrimaryKey(int key);

	Integer saveInterview(String interType, String interComment, String interPlace, String interTime, Integer apId);

	void updateInterview(Interview interview);

	void deleteInterviewByPrimaryKey(int key);

	List<Interview> findAllInterviews();

	void deleteAllInterviews();

	public boolean isInterviewExist(Interview interview);
	
	List<Interview> findByApplicationIds(int key);
	 
	List<Interview> findByApplicationIdAndTimeProcessing(int key);

	List<Interview> findByApplicationIdAndTimeCompleted(int key);

	List<Interview> findByApplicationIdAndTimeExpired(int key);
}
