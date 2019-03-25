package com.funwork.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.User;

@Repository
public class ApplicationDaoImp implements ApplicationDao {

	@Autowired
	SessionFactory factory;

	@Override
	public Application findByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Application application = session.get(Application.class, key);
		return application;
	}

	@Override
	public Application findByDate(Date applicationTime) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Application WHERE applicationTime =:applicationTime";
//		session.createQuery(hql)
		return null;
	}

	@Override
	public void insertApplication(Integer userId, Integer jobId, String question) {
		Session session = factory.getCurrentSession();
		Application application = new Application();
		User user = session.get(User.class, userId);
		Job job = session.get(Job.class, jobId);
		application.setApplicationTime(new Timestamp(System.currentTimeMillis()));
		application.setUser(user);
		application.setJob(job);
		application.setAnswer(question);
		application.setAppliedStatus("待回應");
		session.save(application);
	}

	@Override
	public void saveApplication(Application application) {
		Session session = factory.getCurrentSession();
	}

	@Override
	public void updateApplication(Application application) {
		Session session = factory.getCurrentSession();
	}

	@Override
	public void deleteApplicationByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findAllApplicantByJobId(Job job) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Application WHERE job = :job";
		return session.createQuery(hql).setParameter("job", job).getResultList();
	}

	@Override
	public void deleteAllApplications() {
		Session session = factory.getCurrentSession();
	}

	@Override
	public boolean isApplicationExist(Application application) {
		Session session = factory.getCurrentSession();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationByUserId(Integer userId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Application a WHERE (a.user.userId = :userId OR a.job.jobOwner.userId = :userId2) and a.latestMsg IS NOT NULL ORDER BY a.latestMsgTime DESC";
		List<Application> list = session.createQuery(hql).setParameter("userId", userId).setParameter("userId2", userId)
				.getResultList();
		return list;
	}

	@Override
	public void updateLatestMsg(Integer apId, String msg) {
		Session session = factory.getCurrentSession();
		Application ap = session.get(Application.class, apId);
		ap.setLatestMsg(msg);
		ap.setLatestMsgTime(new Timestamp(System.currentTimeMillis()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationByUserIdByTime(Integer userId) {

		Session session = factory.getCurrentSession();
		String hql = "FROM Application a WHERE (a.user.userId = :userId)"
				+ "ORDER BY a.applicationTime ";
		List<Application> list = session.createQuery(hql).setParameter("userId", userId).getResultList();
		return list;
	}

	@Override
	public void refuseUser(Integer apId) {
		Session session = factory.getCurrentSession();
		Application ap = session.get(Application.class, apId);
		ap.setAppliedStatus("已婉拒");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findAllApplications(Integer userId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Application WHERE job.jobOwner.userId = :userId";
		List<Application> list = session.createQuery(hql).setParameter("userId", userId).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> findByJobId(Integer jobId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Application a WHERE a.job.jobId = :jobId";
		if (session.createQuery(hql).setParameter("jobId", jobId).getResultList().size() != 0) {

			List<Application> list = session.createQuery(hql).setParameter("jobId", jobId).getResultList();
			return list;
		} else {
			return null;
		}
	}
}
