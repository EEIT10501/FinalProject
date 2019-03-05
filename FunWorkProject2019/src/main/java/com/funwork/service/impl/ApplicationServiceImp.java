package com.funwork.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;
import com.funwork.service.ApplicationService;

@Service
public class ApplicationServiceImp implements ApplicationService {

	@Autowired
	ApplicationDao dao;
	
	@Transactional
	@Override
	public Application findByPrimaryKey(int key) {
		return dao.findByPrimaryKey(key);
	}
	
	@Transactional
	@Override
	public Application findByDate(Date date) {
		return dao.findByDate(date);
	}

	@Transactional
	@Override
	public void saveApplication(Application Application) {
		dao.saveApplication(Application);
	}

	@Transactional
	@Override
	public void updateApplication(Application Application) {
		dao.updateApplication(Application);
	}

	@Transactional
	@Override
	public void deleteApplicationByPrimaryKey(int key) {
		dao.deleteAllApplications();
	}

	@Transactional
	@Override
	public List<Application> findAllApplications() {
		return dao.findAllApplications();
	}

	@Transactional
	@Override
	public void deleteAllApplications() {
		dao.deleteAllApplications();
	}

	@Transactional
	@Override
	public boolean isApplicationExist(Application Application) {
		return dao.isApplicationExist(Application);
	}

}
