package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.PropertyNotFoundException;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Schedule> getSchedulesByJobId(Integer jobId) {
		String hql = "FROM Schedule where Fk_Job_Id = :jobId";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("jobId", jobId).getResultList();
		return list;
	}

	@Override
	public void insertSchedule(Schedule schedule) {
		Session session = factory.getCurrentSession();
		session.merge(schedule);
	}

	@Override
	public void deleteScheduleByPrimaryKey(int scheduleId) {
		Session session = factory.getCurrentSession();
		Schedule schedule = new Schedule();
		schedule.setScheduleId(scheduleId);
		session.delete(schedule);
	}

	@Override
	public void updateScheduleByPrimaryKey(Schedule schedule) {
		Session session = factory.getCurrentSession();
		session.update(schedule);
	}

	@Override
	public Schedule getScheduleByPrimaryKey(int scheduleId) {
		Session session = factory.getCurrentSession();
		Schedule schedule = session.get(Schedule.class, scheduleId);
		if (schedule == null)
			throw new PropertyNotFoundException("scheduleId" + scheduleId + " Not Found:");
		return schedule;

	}

}
