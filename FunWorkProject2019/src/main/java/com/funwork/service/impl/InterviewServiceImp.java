package com.funwork.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ApplicationDao;
import com.funwork.dao.InterviewDao;
import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.service.InterviewService;

@Transactional
@Service
public class InterviewServiceImp implements InterviewService {

	@Autowired
	InterviewDao interviewDao;
	@Autowired
	ApplicationDao applicationDao;

	@Override
	public Interview findByPrimaryKey(int key) {
		return interviewDao.findByPrimaryKey(key);
	}

	@Override
	public Integer saveInterview(String interType, String interComment, String interPlace, String interTime,
			Integer apId) {
		Interview interview = new Interview();
		interview.setInterviewType(interType);
		interview.setInterviewComment(interComment);
		interview.setInterviewPlace(interPlace);
		interview.setInterviewTime(Timestamp.valueOf(interTime.replace("T", " ") + ":00"));
		Application ap = applicationDao.findByPrimaryKey(apId);
		interview.setApplication(ap);
		interview.setInterviewStatus("待回應");
		interviewDao.saveInterview(interview);
		return ap.getJob().getJobId();
	}

	@Override
	public void updateInterview(Interview interview) {
		interviewDao.updateInterview(interview);
	}

	@Override
	public void deleteInterviewByPrimaryKey(int key) {
		interviewDao.deleteInterviewByPrimaryKey(key);
	}

	@Override
	public List<Interview> findAllInterviews() {
		return interviewDao.findAllInterviews();
	}

	@Override
	public void deleteAllInterviews() {
		interviewDao.deleteAllInterviews();
	}

	@Override
	public boolean isInterviewExist(Interview interview) {
		return interviewDao.isInterviewExist(interview);
	}

	@Override
	public List<Application> getInterviewByApplicationId(Integer applicationId) {
		return interviewDao.getInterviewByApplicationId(applicationId);
	}

}
