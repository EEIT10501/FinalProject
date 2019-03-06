package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.JobDao;
import com.funwork.model.Job;

@Repository
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory factory;

	public JobDaoImpl() {
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Job> getAllJobs() {
		String hql = "FROM Job";
		Session session = null;
		List<Job> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobReviewList() {
		String hql = "FROM Job WHERE reviewStatus = '待審核' ORDER BY submitTime ASC";
		List<Job> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobPassed() {
		String hql = "FROM Job WHERE reviewStatus = '發布中' ORDER BY submitTime ASC";
		List<Job> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobByCity(Integer cityId) {
		String hql = "FROM Job WHERE Fk_City_Id = :cityId ORDER BY submitTime ASC";
		List<Job> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql).setParameter("cityId", cityId);
		list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJobByCityArea(Integer cityId) {
		String hql = "";
		if (cityId <= 12) {
			hql = "FROM Job WHERE Fk_City_Id <= 12 ORDER BY submitTime ASC";
		}
		if (cityId >= 13 && cityId < 42) {
			hql = "FROM Job WHERE Fk_City_Id BETWEEN 13 AND 41 ORDER BY submitTime ASC";
		}
		List<Job> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public Job getJobById(Integer jobId) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class, jobId);
		return job;
	}

	@Override
	public Job jobReviewPass(Integer jobId) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class, jobId);
		job.setReviewStatus("發布中");
		return job;
	}

	@Override
	public Job jobReviewFail(Integer jobId, String failReason) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class, jobId);
		job.setReviewStatus("審核失敗");
		job.setFailReason(failReason);
		return job;
	}

	@Override
	public Job jobRemove(Integer jobId, String removeReason) {
		Session session = factory.getCurrentSession();
		Job job = session.get(Job.class, jobId);
		job.setReviewStatus("下架");
		job.setFailReason(removeReason);
		return job;
	}

}
