package com.funwork.service;

import com.funwork.model.User;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User findByPrimaryKey(int key);

  User getUserById(Integer userId);

  Integer insertUser(String email, String name, String password);

  public boolean idExists(String email);

  User loginCheck(String email, String password);

  void openUser(Integer userId);
  
  void updateUser(User user);

  User getUserByGoogleEmail(String email, String googleId);

  Integer insertGoogleUser(String email, String name, String googleId);

  void updateAccount(String email, String password, Integer userId);;
}
