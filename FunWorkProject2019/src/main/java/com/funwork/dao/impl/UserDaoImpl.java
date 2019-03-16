package com.funwork.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.UserDao;
import com.funwork.model.User;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  SessionFactory factory;

  @Override
  @SuppressWarnings("unchecked")
  public List<User> getAllUsers() {
    String hql = "FROM User";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public User findByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    return session.get(User.class, key);
  }

  @Override
  public User getUserById(Integer userId) {
    Session session = factory.getCurrentSession();
    return session.get(User.class, userId);
  }

  @Override
  public Integer insertUser(User user) {
    Session session = factory.getCurrentSession();
    return (Integer) session.save(user);
  }

  @SuppressWarnings("unchecked")
  @Override
  public User loginCheck(String email, String password) {
    User user = null;
    String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
    Session session = factory.getCurrentSession();
    List<User> list = session.createQuery(hql).setParameter("email", email).setParameter("password", password)
        .getResultList();
    if (list.isEmpty()) {
      user = null;
    } else {
      user = list.get(0);
    }
    return user;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean idExists(String email) {
    Session session = factory.getCurrentSession();
    boolean exist = false;
    String hql = "FROM User u WHERE u.email = :email";
    List<User> list = session.createQuery(hql).setParameter("email", email).getResultList();
    if (!list.isEmpty()) {
      exist = true;
    } else {
      exist = false;
    }

    return exist;
  }

  @Override
  public void openUser(Serializable userId) {
    Session session = factory.getCurrentSession();
    String hql = "UPDATE User u SET u.isOpen = 1 WHERE u.userId = :userId";
    session.createQuery(hql).setParameter("userId", Integer.valueOf(userId.toString())).executeUpdate();
  }

  @Override
  public User getUserByGoogleEmail(String email, String googleId) {
    Session session = factory.getCurrentSession();
    String hql = "UPDATE User u SET u.google = :googleId WHERE u.email = :email";
    String hql2 = "FROM User u WHERE u.email = :email";
    session.createQuery(hql).setParameter("googleId", googleId).setParameter("email", email).executeUpdate();
    return (User) session.createQuery(hql2).setParameter("email", email).uniqueResult();
  }

}
