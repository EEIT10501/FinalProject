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
	@Transactional
	@Override
	public User findByPrimaryKey(int key) {
		return dao.findByPrimaryKey(key);
	}

	@Transactional
	@Override
	public User getUserById(Integer userId) {
		return dao.getUserById(userId);
	}

	@Transactional
	@Override
	public void insertUser(User user) {
		dao.insertUser(user);
	}

	@Transactional
	@Override
	public User loginCheck(String email, String password) {
		return dao.loginCheck(email, password);
	}

}
