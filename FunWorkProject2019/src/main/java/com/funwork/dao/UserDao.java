package com.funwork.dao;

import com.funwork.model.User;

public interface UserDao {

  User getUserById(Integer userId);

  Integer insertUser(User user);

  public boolean idExist(String email);

  User loginCheck(String email, String password);

  void updateUser(User user);

  User getUserByGoogleEmail(String email, String googleId);

  User getUserByEmail(String email);
}