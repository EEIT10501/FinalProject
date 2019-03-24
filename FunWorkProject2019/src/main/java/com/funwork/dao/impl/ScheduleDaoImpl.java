package com.funwork.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.PropertyNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ScheduleDao;
import com.funwork.model.Interview;
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

	@Override
	public List<Schedule> getSchedulesByDate(Integer jobId) {
		String hql = "FROM Schedule s WHERE endTime > :endTime AND s.interview.interviewType='錄取' AND s.interview.application.job.jobId=:jobId";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();

		Date date = new Date();
//		System.out.println(date);
		list = session.createQuery(hql).setParameter("endTime", date).setParameter("jobId", jobId).getResultList();
		return list;
	}
	
	@Override
	public List<Schedule> getSchedulesByUser(Integer userId) {
		String hql = "FROM Schedule s WHERE endTime > :endTime AND s.interview.interviewType='錄取' AND s.interview.application.user.userId=:userId";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();

		Date date = new Date();
		list = session.createQuery(hql).setParameter("endTime", date).setParameter("userId", userId).getResultList();
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
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Schedule> getSchedulesByJobIdAndTime(Integer jobId , Timestamp endTime , Timestamp startTime) {
		String hql = "FROM Schedule s WHERE s.interview.interviewType='錄取' AND s.interview.application.job.jobId=:jobId"
				+ " AND s.startTime <= :startTime "
				+ " AND s.endTime >= :endTime";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("jobId", jobId).setParameter("startTime", startTime).setParameter("endTime", endTime).getResultList();
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Schedule> getSchedulesByNameAndTime(String scheduleName , Timestamp endTime , Timestamp startTime) {
		String hql = "FROM Schedule s WHERE s.scheduleName =:scheduleName"
				+ " AND s.startTime <= :startTime "
				+ " AND s.endTime >= :endTime";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("scheduleName", scheduleName).setParameter("startTime", startTime).setParameter("endTime", endTime).getResultList();
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Schedule> getSchedulesByAdmit(Integer interviewId) {
		String hql = "FROM Schedule s WHERE s.interview.interviewId = :interviewId";				
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("interviewId", interviewId).getResultList();
		return list;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Schedule> getSchedulesByName(String scheduleName) {
		String hql = "FROM Schedule s WHERE s.scheduleName = :scheduleName";				
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("scheduleName", scheduleName).getResultList();
		return list;
	}
	

	@Override
	public void insertSchedule(Schedule schedule) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(schedule);
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
	
	@Override
	public List<Schedule> getUserScheduleByRange(Integer userId,Date time1,Date time2){
		String hql = "FROM Schedule s WHERE endTime BETWEEN :time1 AND :time2 AND s.interview.interviewType='錄取' AND s.interview.application.user.userId=:userId";
		
		Session session = factory.getCurrentSession();
		List<Schedule> list = session.createQuery(hql).setParameter("time1", time1).setParameter("time2", time2).setParameter("userId", userId).getResultList();
		
		return list;
		
	}
	
	@Override
	public List<Schedule> getJobSchedulesByRange(Integer jobId,Date time1,Date time2) {
		String hql = "FROM Schedule s WHERE endTime BETWEEN :time1 AND :time2 AND s.interview.interviewType='錄取' AND s.interview.application.job.jobId=:jobId";
		Session session = null;
		List<Schedule> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("time1", time1).setParameter("time2", time2).setParameter("jobId", jobId).getResultList();
		return list;
	}

}
