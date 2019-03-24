package com.funwork.dao.impl;

import com.funwork.dao.UserDao;
import com.funwork.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
  private static final String EMAIL_STR = "email";
  private static final String HQL_GET_USER_BY_EMAIL = "FROM User u WHERE u.email = :email";
  @Autowired
  SessionFactory factory;

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
    List<User> list = session.createQuery(hql).setParameter(EMAIL_STR, email)
        .setParameter("password", password)
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
  public boolean idExist(String email) {
    Session session = factory.getCurrentSession();
    boolean exist = false;
    List<User> list = session.createQuery(HQL_GET_USER_BY_EMAIL)
        .setParameter(EMAIL_STR, email).getResultList();
    if (!list.isEmpty()) {
      exist = true;
    } else {
      exist = false;
    }
    return exist;
  }

  @Override
  public void updateUser(User user) {
    Session session = factory.getCurrentSession();
    session.update(user);
  }

  @Override
  public User getUserByGoogleEmail(String email, String googleId) {
    Session session = factory.getCurrentSession();
    String hql = "UPDATE User u SET u.google = :googleId WHERE u.email = :email";
    session.createQuery(hql).setParameter("googleId", googleId)
    .setParameter(EMAIL_STR, email).executeUpdate();
    return (User) session.createQuery(HQL_GET_USER_BY_EMAIL)
        .setParameter(EMAIL_STR, email).uniqueResult();
  }

  @SuppressWarnings("unchecked")
  @Override
  public User getUserByEmail(String email) {
    User user = null;
    Session session = factory.getCurrentSession();
    List<User> list = session.createQuery(HQL_GET_USER_BY_EMAIL)
        .setParameter(EMAIL_STR, email).getResultList();
    if (!list.isEmpty()) {
      user = list.get(0);
    }
    return user;
  }
}