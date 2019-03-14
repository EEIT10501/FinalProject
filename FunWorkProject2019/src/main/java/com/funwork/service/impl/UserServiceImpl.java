package com.funwork.service.impl;

import java.io.Serializable;
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
	public Serializable insertUser(User user) {
		return dao.insertUser(user);
	}

	@Transactional
	@Override
	public User loginCheck(String email, String password) {
		return dao.loginCheck(email, password);
	}

	@Transactional
	@Override
	public boolean idExists(String email) {
		return dao.idExists(email);
	}

	@Transactional
	@Override
	public void openUser(Serializable userId) {
		dao.openUser(userId);
	}

	@Transactional
	@Override
	public User getUserByGoogleEmail(String email, String googleId) {
		return dao.getUserByGoogleEmail(email, googleId);
	}

}
