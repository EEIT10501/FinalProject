package com.funwork.dao;

import java.util.List;

import com.funwork.model.Company;
import com.funwork.model.User;

public interface UserDao {

	List<User> getAllUsers();

	User findByPrimaryKey(int key);
}
