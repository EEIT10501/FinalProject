package com.funwork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.funwork.dao.ScheduleDao;
import com.funwork.model.Schedule;
import com.funwork.service.ScheuleService;


@Service
public class ScheduleServiceImpl implements ScheuleService {

	@Autowired
	ScheduleDao dao;

	public ScheduleServiceImpl() {
	}

	@Transactional
	@Override
	public List<Schedule> getAllSchedules() {
		return dao.getAllSchedules();
	}
	
	@Transactional
	@Override
	public List<Schedule> getSchedulesByJobId(Integer jobId) {
		return dao.getSchedulesByJobId(jobId);
	}

}
