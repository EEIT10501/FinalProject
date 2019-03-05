package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ComplaintDao;
import com.funwork.model.Complaint;

@Repository
public class ComplaintDaoImpl implements ComplaintDao {

	@Autowired
	SessionFactory factory;

	public ComplaintDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Complaint> getAllComplaints() {
		String hql = "FROM Complaint";
		Session session = null;
		List<Complaint> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}
