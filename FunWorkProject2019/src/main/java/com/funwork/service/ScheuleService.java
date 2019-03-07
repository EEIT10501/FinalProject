package com.funwork.service;

import java.util.List;

import com.funwork.model.Schedule;

public interface ScheuleService {
	List<Schedule> getAllSchedules();
	
	List<Schedule> getSchedulesByJobId(Integer jobId);
}
