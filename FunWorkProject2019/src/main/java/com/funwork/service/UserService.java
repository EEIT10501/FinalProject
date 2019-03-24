package com.funwork.service;

import com.funwork.model.User;

public interface UserService {
  User getUserById(Integer userId);

  Integer insertUser(String email, String name, String password);

  public boolean idExist(String email);

  User loginCheck(String email, String password);

  void openUser(Integer userId);

  void updateUser(User user);

  User getUserByGoogleEmail(String email, String googleId);

  Integer insertGoogleUser(String email, String name, String googleId);

  User getUserByEmail(String email);

  void updatePassword(String password, Integer userId);
}