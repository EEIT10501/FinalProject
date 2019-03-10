package com.funwork.dao;

import java.util.List;

import com.funwork.model.User;

public interface UserDao {

	List<User> getAllUsers();

	User getUserById(Integer userId);
	
	void insertUser(User user);
	
	User loginCheck(String email, String password);
}
