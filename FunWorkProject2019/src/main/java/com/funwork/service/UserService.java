package com.funwork.service;

import java.util.List;

import com.funwork.model.User;

public interface UserService {

	List<User> getAllUsers();

	User findByPrimaryKey(int key);
}
