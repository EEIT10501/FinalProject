package com.funwork.dao;

import java.io.Serializable;
import java.util.List;

import com.funwork.model.User;

public interface UserDao {

	List<User> getAllUsers();

	User findByPrimaryKey(int key);
	
	User getUserById(Integer userId);
	
	Serializable insertUser(User user);
	
	public boolean idExists(String email);
	
	User loginCheck(String email, String password);
	
	void openUser(Serializable userId);
}
