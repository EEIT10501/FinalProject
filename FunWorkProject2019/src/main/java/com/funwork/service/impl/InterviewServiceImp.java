package com.funwork.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.InterviewDao;
import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.service.InterviewService;

@Service
public class InterviewServiceImp implements InterviewService {

	@Autowired
	InterviewDao dao;
	
	@Transactional
	@Override
	public Interview findByPrimaryKey(int key) {
		return dao.findByPrimaryKey(key);
	}

	@Transactional
	@Override
	public void saveInterview(Interview interview) {
		dao.saveInterview(interview);
	}

	@Transactional
	@Override
	public void updateInterview(Interview interview) {
		dao.updateInterview(interview);
	}

	@Transactional
	@Override
	public void deleteInterviewByPrimaryKey(int key) {
		dao.deleteInterviewByPrimaryKey(key);
	}

	@Transactional
	@Override
	public List<Interview> findAllInterviews() {
		return dao.findAllInterviews();
	}

	@Transactional
	@Override
	public void deleteAllInterviews() {
		dao.deleteAllInterviews();
	}

	@Transactional
	@Override
	public boolean isInterviewExist(Interview interview) {
		return dao.isInterviewExist(interview);
	}
	

	@Transactional
	@Override
	public List<Interview> findByApplicationIds(int key) {
		return dao.findByApplicationIds(key);
	} 
	
	@Transactional
	@Override
	public List<Interview> findByApplicationIdAndTimeProcessing(int key) {
		return dao.findByApplicationIdAndTimeProcessing(key);
	}
	@Transactional
	@Override
	public List<Interview> findByApplicationIdAndTimeCompleted(int key) {
		return dao.findByApplicationIdAndTimeCompleted(key);
	}
	@Transactional
	@Override
	public List<Interview> findByApplicationIdAndTimeExpired(int key) {
		return dao.findByApplicationIdAndTimeExpired(key);
	}
	
}
