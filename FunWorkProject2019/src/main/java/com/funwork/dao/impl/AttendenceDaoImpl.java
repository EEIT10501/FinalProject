package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.AttendenceDao;
import com.funwork.model.Attendence;

@Repository
public class AttendenceDaoImpl implements AttendenceDao {

	@Autowired
	SessionFactory factory;

	@Override
	@SuppressWarnings("unchecked")
	public List<Attendence> getAllAttendences() {
		String hql = "FROM Attendence";
		Session session = null;
		List<Attendence> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}
