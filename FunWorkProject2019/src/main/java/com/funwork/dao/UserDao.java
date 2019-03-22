package com.funwork.dao;

import com.funwork.model.User;
import java.util.List;

public interface UserDao {

  List<User> getAllUsers();

  User findByPrimaryKey(int key);

  User getUserById(Integer userId);

  Integer insertUser(User user);

  public boolean idExists(String email);

  User loginCheck(String email, String password);

  void updateUser(User user);

  User getUserByGoogleEmail(String email, String googleId);

  User getUserByEmail(String email);

  void updateAccount(String email, String password, Integer userId);

}
