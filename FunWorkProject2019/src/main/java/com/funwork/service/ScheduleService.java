package com.funwork.service;

import com.funwork.model.Schedule;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ScheduleService {

  List<Schedule> getSchedulesByDate(Integer jobId);

  List<Schedule> getSchedulesByUser(Integer userId);

  void insertSchedule(Schedule schedule);

  void deleteScheduleByPrimaryKey(int scheduleId);

  List<Schedule> getSchedulesByJobIdAndTime(Integer jobId, Timestamp endTime, Timestamp startTime);

  List<Schedule> getSchedulesByNameAndTime(String scheduleName, 
      Timestamp endTime, Timestamp startTime);

  List<Schedule> getUserScheduleByRange(Integer userId, Date time1, Date time2);

  List<Schedule> getJobSchedulesByRange(Integer jobId, Date time1, Date time2);
}