package com.funwork.service.impl;

import com.funwork.dao.UserDao;
import com.funwork.model.User;
import com.funwork.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao dao;

  @Override
  public List<User> getAllUsers() {
    return dao.getAllUsers();
  }

  @Override
  public User findByPrimaryKey(int key) {
    return dao.findByPrimaryKey(key);
  }

  @Override
  public User getUserById(Integer userId) {
    return dao.getUserById(userId);
  }

  @Override
  public Integer insertUser(String email, String name, String password) {
    User user = new User();
    user.setEmail(email);
    user.setUserName(name);
    user.setPassword(password);
    user.setRole(2);
    user.setAbscence(0);
    user.setExposureLimit(0);
    user.setJobPostLimit(3);
    user.setJobPostPeriod(7);
    user.setMebershipLevel(1);
    user.setIsOpen(false);
    return dao.insertUser(user);
  }

  @Override
  public User loginCheck(String email, String password) {
    return dao.loginCheck(email, password);
  }

  @Override
  public boolean idExists(String email) {
    return dao.idExists(email);
  }

  @Override
  public void openUser(Integer userId) {
    User user = dao.getUserById(userId);
    user.setIsOpen(true);
    dao.updateUser(user);
  }

  @Override
  public User getUserByGoogleEmail(String email, String googleId) {
    return dao.getUserByGoogleEmail(email, googleId);
  }

  @Override
  public Integer insertGoogleUser(String email, String name, String googleId) {
    User user = new User();
    user.setEmail(email);
    user.setUserName(name);
    user.setGoogle(googleId);
    user.setRole(2);
    user.setAbscence(0);
    user.setExposureLimit(0);
    user.setJobPostLimit(3);
    user.setJobPostPeriod(7);
    user.setMebershipLevel(1);
    user.setIsOpen(true);
    return dao.insertUser(user);
  }

  @Override
  public User getUserByEmail(String email) {
    return  dao.getUserByEmail(email);
  }
}