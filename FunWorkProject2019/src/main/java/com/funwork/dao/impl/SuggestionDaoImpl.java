package com.funwork.dao.impl;

import com.funwork.dao.SuggestionDao;
import com.funwork.model.Suggestion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SuggestionDaoImpl implements SuggestionDao {

  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Suggestion> getAllSuggestions() {
    String hql = "FROM Suggestion";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public Suggestion getSuggestionById(Integer sgId) {
    Session session = factory.getCurrentSession();
    return session.get(Suggestion.class, sgId);
  }

  @Override
  public void insertSg(Suggestion sg) {
    Session session = factory.getCurrentSession();
    session.save(sg);
  }
}