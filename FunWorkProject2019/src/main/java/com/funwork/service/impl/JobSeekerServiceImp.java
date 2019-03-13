package com.funwork.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ApplicationDao;
import com.funwork.dao.UserDao;
import com.funwork.model.Application;
import com.funwork.model.User;
import com.funwork.service.ApplicationService;
import com.funwork.service.UserService;

//@Service
//public class JobSeekerServiceImp implements ApplicationService, UserService  {
//
//	@Autowired
//	ApplicationDao applicationDao;
//	
//	@Autowired
//	UserDao userDao;
//	
//	@Transactional
//	@Override
//	public User getUserById(Integer userId) {
//		return userDao.getUserById(userId);
//	}
	


//	@Transactional
//	@Override
//	public boolean isApplicationExist(Application Application) {
//		return dao.isApplicationExist(Application);
//	}
//
//}
