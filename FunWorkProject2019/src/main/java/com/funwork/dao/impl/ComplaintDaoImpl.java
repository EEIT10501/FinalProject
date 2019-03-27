package com.funwork.dao.impl;

import com.funwork.dao.ComplaintDao;
import com.funwork.model.Complaint;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintDaoImpl implements ComplaintDao {
  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Complaint> getComplaintProcessList() {
    String hql = "FROM Complaint WHERE status = '待處理' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public Complaint getComplaintById(Integer cpId) {
    Session session = factory.getCurrentSession();
    return session.get(Complaint.class, cpId);
  }

  @Override
  public void processComplaint(Complaint cp) {
    Session session = factory.getCurrentSession();
    session.update(cp);
  }

  @Override
  public void insertCp(Complaint cp) {
    Session session = factory.getCurrentSession();
    session.save(cp);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Complaint> getComplaintHistoryList() {
    String hql = "FROM Complaint WHERE status = '已處理' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }
}