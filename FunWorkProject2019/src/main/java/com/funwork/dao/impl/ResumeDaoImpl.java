package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ResumeDao;
import com.funwork.model.Resume;

@Repository
public class ResumeDaoImpl implements ResumeDao {

	@Autowired
	SessionFactory factory;

	public ResumeDaoImpl() {

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Resume> getAllResumes() {
		String hql = "FROM Resume";
		Session session = null;
		List<Resume> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}