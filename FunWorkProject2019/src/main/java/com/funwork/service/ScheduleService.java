package com.funwork.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.funwork.model.Interview;
import com.funwork.model.Schedule;

public interface ScheduleService {
	List<Schedule> getAllSchedules();
	
	List<Schedule> getSchedulesByDate(Integer jobId);
	
	List<Schedule> getSchedulesByUser(Integer userId);

//	List<Schedule> getSchedulesByJobId(Integer jobId);

	void insertSchedule(Schedule schedule);

	void deleteScheduleByPrimaryKey(int scheduleId);

	Schedule getScheduleByPrimaryKey(int scheduleId);

	void updateScheduleByPrimaryKey(Schedule schedule);

	List<Schedule> getSchedulesByAdmit(Integer useId);

	List<Schedule> getSchedulesByJobIdAndTime(Integer jobId, Timestamp endTime, Timestamp startTime);

	List<Schedule> getSchedulesByName(String scheduleName);

	List<Schedule> getSchedulesByNameAndTime(String scheduleName, Timestamp endTime, Timestamp startTime);
	
	List<Schedule> getUserScheduleByRange(Integer userId,Date time1,Date time2);
	
	List<Schedule> getJobSchedulesByRange(Integer jobId,Date time1,Date time2);
	

}
