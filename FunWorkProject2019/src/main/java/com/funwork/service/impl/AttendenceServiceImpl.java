package com.funwork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.AttendenceDao;
import com.funwork.model.Attendence;
import com.funwork.service.AttendenceService;

@Service
public class AttendenceServiceImpl implements AttendenceService {

	@Autowired
	AttendenceDao dao;

	@Transactional
	@Override
	public List<Attendence> getAllAttendences() {
		return dao.getAllAttendences();
	}

}
