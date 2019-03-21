package com.funwork.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ScheduleDao;
import com.funwork.model.Interview;
import com.funwork.model.Schedule;
import com.funwork.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDao dao;

	@Transactional
	@Override
	public List<Schedule> getAllSchedules() {
		return dao.getAllSchedules();
	}
	
	@Transactional
	@Override
	public List<Schedule> getSchedulesByDate(Integer jobId) {
		return dao.getSchedulesByDate(jobId);
	}

	@Transactional
	@Override
	public List<Schedule> getSchedulesByJobId(Integer jobId) {
		return dao.getSchedulesByJobId(jobId);
	}

	@Transactional
	@Override
	public void insertSchedule(Schedule schedule) {
		dao.insertSchedule(schedule);
	}

	@Transactional
	@Override
	public void deleteScheduleByPrimaryKey(int scheduleId) {
		dao.deleteScheduleByPrimaryKey(scheduleId);
	}

	@Transactional
	@Override
	public void updateScheduleByPrimaryKey(Schedule schedule) {
		dao.updateScheduleByPrimaryKey(schedule);
	}

	@Transactional
	@Override
	public Schedule getScheduleByPrimaryKey(int scheduleId) {
		return dao.getScheduleByPrimaryKey(scheduleId);
	}
	
	@Transactional
	@Override
	public List<Schedule> getSchedulesByAdmit(Integer useId) {
		return dao.getSchedulesByAdmit(useId);
	}
	
	@Transactional
	@Override
	public List<Schedule> getSchedulesByJobIdAndTime(Integer jobId, Timestamp endTime, Timestamp startTime) {
		return dao.getSchedulesByJobIdAndTime(jobId, endTime,  startTime);
	}

}
