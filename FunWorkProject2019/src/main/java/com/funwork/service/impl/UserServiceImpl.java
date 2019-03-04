package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.UserDao;
import com.funwork.model.User;
import com.funwork.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	public UserServiceImpl() {
	}

	@Transactional
	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

}
