package com.funwork.dao;

import java.util.List;

import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.User;

public interface InterviewDao {
	Interview findByPrimaryKey(int key);
	
	void saveInterview(Interview interview);

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

	List<Interview> findInterviewsByJobOwnerId(Job job);

	List<Interview> findInterviewsByJobOwner(User jobOwner);

}
