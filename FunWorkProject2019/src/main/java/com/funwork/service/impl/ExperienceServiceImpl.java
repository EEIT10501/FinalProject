package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ExperienceDao;
import com.funwork.model.Experience;
import com.funwork.service.ExperienceService;




@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	ExperienceDao dao;

	public ExperienceServiceImpl() {
	}

	@Transactional
	@Override
	public List<Experience> getAllExperiences() {
		return dao.getAllExperiences();
	}

}
