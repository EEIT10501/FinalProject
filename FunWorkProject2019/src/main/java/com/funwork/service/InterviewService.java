package com.funwork.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.User;

public interface InterviewService {

	Interview findByPrimaryKey(int key);

	Integer saveInterview(String interType, String interComment, String interPlace, String interTime, Integer apId);

	void updateInterviewResult(String interviewResult, int key);

	void updateInterview(Interview interview);
	
	void deleteInterviewByPrimaryKey(int key);

	List<Interview> findAllInterviews();

	void deleteAllInterviews();

	public boolean isInterviewExist(Interview interview);
	
	List<Interview> findByApplicationIds(int key);
	 
	List<Interview> findByApplicationIdAndTimeProcessing(int key);

	List<Interview> findByApplicationIdAndTimeCompleted(int key);

	List<Interview> findByApplicationIdAndTimeExpired(int key);
	
	List<Interview> findInterviewByAdmit(Integer jobId);
	
	Interview findByAdmit_Job_UserName(Integer jobId,String userName);

	public List<Interview> findInterviewsByJobOwner(User jobOwner);
	
}
