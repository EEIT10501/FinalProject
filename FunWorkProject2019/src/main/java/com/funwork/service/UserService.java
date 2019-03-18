package com.funwork.service;

import java.io.Serializable;
import java.util.List;

import com.funwork.model.User;

public interface UserService {

	List<User> getAllUsers();

	User findByPrimaryKey(int key);

	User getUserById(Integer userId);

	Integer insertUser(String email, String name, String password);

	public boolean idExists(String email);

	User loginCheck(String email, String password);

	void openUser(Serializable userId);

	User getUserByGoogleEmail(String email, String googleId);
	
	Integer insertGoogleUser(String email, String name, String googleId);
}
