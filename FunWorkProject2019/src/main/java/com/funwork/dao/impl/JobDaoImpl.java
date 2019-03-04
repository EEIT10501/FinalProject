package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
		String hql = "FROM Job WHERE reviewStatus = '待審核'";
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

}
