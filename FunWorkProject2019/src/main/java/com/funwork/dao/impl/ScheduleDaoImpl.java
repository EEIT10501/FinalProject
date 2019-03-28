package com.funwork.dao.impl;

import com.funwork.dao.ScheduleDao;
import com.funwork.model.Schedule;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
  private static final String END_TIME = "endTime";
  private static final String JOBID = "jobId";
  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getSchedulesByDate(Integer jobId) {
    String hql = "FROM Schedule s WHERE endTime > :endTime "
        + "AND s.interview.interviewType='錄取' AND s.interview.application.job.jobId=:jobId";
    Session session = null;
    session = factory.getCurrentSession();
    Date date = new Date();
    return session.createQuery(hql).setParameter(END_TIME, date)
        .setParameter(JOBID, jobId).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getSchedulesByUser(Integer userId) {
    String hql = "FROM Schedule s WHERE endTime > :endTime "
        + "AND s.interview.interviewType='錄取' AND s.interview.application.user.userId=:userId";
    Session session = null;
    session = factory.getCurrentSession();

    Date date = new Date();
    return session.createQuery(hql).setParameter(END_TIME, date)
        .setParameter(JOBID, userId).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getSchedulesByJobIdAndTime(Integer jobId, Timestamp endTime, 
      Timestamp startTime) {
    String hql = "FROM Schedule s WHERE s.interview.interviewType='錄取' "
        + "AND s.interview.application.job.jobId=:jobId"
        + " AND s.startTime <= :startTime " + " AND s.endTime >= :endTime";
    Session session = null;
    session = factory.getCurrentSession();

    return session.createQuery(hql).setParameter(JOBID, jobId)
        .setParameter("startTime", startTime)
        .setParameter(END_TIME, endTime).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getSchedulesByNameAndTime(String scheduleName, Timestamp endTime, 
      Timestamp startTime) {
    String hql = "FROM Schedule s WHERE s.scheduleName =:scheduleName" 
        + " AND s.startTime <= :startTime "
        + " AND s.endTime >= :endTime";
    Session session = null;
    session = factory.getCurrentSession();

    return session.createQuery(hql).setParameter("scheduleName", scheduleName)
        .setParameter("startTime", startTime)
        .setParameter(END_TIME, endTime).getResultList();
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

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getUserScheduleByRange(Integer userId, Date time1, Date time2) {
    String hql = "FROM Schedule s WHERE endTime BETWEEN :time1 "
        + "AND :time2 AND s.interview.interviewType='錄取' "
        + "AND s.interview.application.user.userId=:userId";

    Session session = factory.getCurrentSession();

    return session.createQuery(hql).setParameter("time1", time1).setParameter("time2", time2)
        .setParameter("userId", userId).getResultList();

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Schedule> getJobSchedulesByRange(Integer jobId, Date time1, Date time2) {
    String hql = "FROM Schedule s WHERE endTime BETWEEN :time1 AND :time2 "
        + "AND s.interview.interviewType='錄取' AND s.interview.application.job.jobId=:jobId";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter("time1", time1)
        .setParameter("time2", time2)
        .setParameter(JOBID, jobId).getResultList();
  }
}