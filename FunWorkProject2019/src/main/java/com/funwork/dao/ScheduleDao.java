package com.funwork.dao;

import java.util.List;

import com.funwork.model.Schedule;

public interface ScheduleDao {
	List<Schedule> getAllSchedules();
	
	List<Schedule> getSchedulesByJobId(Integer jobId);

	void insertSchedule(Schedule schedule);

	void deleteScheduleByPrimaryKey(int scheduleId);

	Schedule getScheduleByPrimaryKey(int scheduleId);

	void updateScheduleByPrimaryKey(Schedule schedule);

}
