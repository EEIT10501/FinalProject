package com.funwork.service.impl;

import com.funwork.dao.ScheduleDao;
import com.funwork.model.Schedule;
import com.funwork.service.ScheduleService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  ScheduleDao dao;

  @Override
  public List<Schedule> getSchedulesByDate(Integer jobId) {
    return dao.getSchedulesByDate(jobId);
  }

  @Override
  public List<Schedule> getSchedulesByUser(Integer userId) {
    return dao.getSchedulesByUser(userId);
  }

  @Override
  public void insertSchedule(Schedule schedule) {
    dao.insertSchedule(schedule);
  }

  @Override
  public void deleteScheduleByPrimaryKey(int scheduleId) {
    dao.deleteScheduleByPrimaryKey(scheduleId);
  }

  @Override
  public List<Schedule> getSchedulesByJobIdAndTime(Integer jobId, 
      Timestamp endTime, Timestamp startTime) {
    return dao.getSchedulesByJobIdAndTime(jobId, endTime, startTime);
  }

  @Override
  public List<Schedule> getSchedulesByNameAndTime(String scheduleName, 
      Timestamp endTime, Timestamp startTime) {
    return dao.getSchedulesByNameAndTime(scheduleName, endTime, startTime);
  }

  @Override
  public List<Schedule> getUserScheduleByRange(Integer userId, Date time1, Date time2) {
    return dao.getUserScheduleByRange(userId, time1, time2);
  }

  @Override
  public List<Schedule> getJobSchedulesByRange(Integer jobId, Date time1, Date time2) {
    return dao.getJobSchedulesByRange(jobId, time1, time2);
  }
}