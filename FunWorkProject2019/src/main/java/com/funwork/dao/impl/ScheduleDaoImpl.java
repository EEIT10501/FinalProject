package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ScheduleDao;
import com.funwork.model.Schedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

	@Autowired
	SessionFactory factory;

	public ScheduleDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getAllSchedules() {
		String hql = "FROM Schedule";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> findSchedulesGreaterThan(String time) {
		String hql = "FROM Schedule WHERE endTime > '" + time + "'";
		Session session = factory.getCurrentSession();
		List<Schedule> list = session.createQuery(hql).getResultList();
		return list;
	}

}
