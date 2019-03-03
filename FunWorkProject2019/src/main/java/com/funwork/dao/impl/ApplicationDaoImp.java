package com.funwork.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;

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
	public void saveApplication(Application Application) {
		Session session = factory.getCurrentSession();
	}

	@Override
	public void updateApplication(Application Application) {
		Session session = factory.getCurrentSession();
	}

	@Override
	public void deleteApplicationByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
	}

	@Override
	public List<Application> findAllApplications() {
		Session session = factory.getCurrentSession();
		return null;
	}

	@Override
	public void deleteAllApplications() {
		Session session = factory.getCurrentSession();
	}

	@Override
	public boolean isApplicationExist(Application Application) {
		Session session = factory.getCurrentSession();
		return false;
	}

}
