package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.InterviewDao;
import com.funwork.model.Application;
import com.funwork.model.Interview;
import com.funwork.model.Schedule;

@Repository
public class InterviewDaoImp implements InterviewDao {

	@Autowired
	SessionFactory factory;

	@Override
	public Interview findByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Interview interview = session.get(Interview.class, key);
		return interview;
	}
	

	@Override
	public List<Application> getInterviewByApplicationId(Integer  applicationId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Interview a WHERE (a.application.applicationId = :applicationId)"+"ORDER BY a.applicationTime DESC";
		List<Application> list = session.createQuery(hql).setParameter("userId", applicationId).getResultList();
		return list;
	}
	

	@Override
	public void saveInterview(Interview interview) {
		Session session = factory.getCurrentSession();
		session.save(interview);
	}

	@Override
	public void updateInterview(Interview interview) {
		Session session = factory.getCurrentSession();
		session.update(interview);
	}

	@Override
	public void deleteInterviewByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Interview interview = new Interview();
		interview.setInterviewId(key);
		session.delete(interview);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Interview> findAllInterviews() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Interview";
		List<Interview> list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void deleteAllInterviews() {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM Interview";
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public boolean isInterviewExist(Interview interview) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM Interview WHERE interviewId=:interviewId";
		try {
			session.createQuery(hql).setParameter(1, interview.getInterviewId()).getResultList();
			exist = true;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return exist;
	}
}
