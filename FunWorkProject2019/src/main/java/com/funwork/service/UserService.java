package com.funwork.service;

import java.util.List;

import com.funwork.model.User;

public interface UserService {

	List<User> getAllUsers();

	User findByPrimaryKey(int key);
	
	User getUserById(Integer userId);
	
	void insertUser(User user);
	
	public boolean idExists(String email);
	
	User loginCheck(String email, String password);
	
	
}
