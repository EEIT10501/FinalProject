package com.funwork.dao.impl;

import com.funwork.dao.MessageDao;
import com.funwork.model.Message;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoImpl implements MessageDao {

  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> getAllMessages() {
    String hql = "FROM Message";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Message> getOldMessageByApplicationId(Integer applicationId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Message WHERE Fk_Application_Id = :applicationId ORDER BY time ASC";
    return session.createQuery(hql).setParameter("applicationId", applicationId).getResultList();
  }

  @Override
  public void insertMessage(Message msg) {
    Session session = factory.getCurrentSession();
    session.save(msg);
  }

  @Override
  public void changeMsgStatusToRead(Integer userId, Integer adId) {
    Session session = factory.getCurrentSession();
    String hql = "UPDATE Message m SET m.status = 1 WHERE m.receiver.userId = :userId "
        + "AND m.application.applicationId = :apId";
    session.createQuery(hql).setParameter("userId", userId)
    .setParameter("apId", adId).executeUpdate();
  }

  @Override
  public int getNewMsgCount(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "SELECT count(*) FROM Message m WHERE m.receiver.userId = :userId " 
        + "AND m.status = 0";
    Long count = (Long) session.createQuery(hql).setParameter("userId", userId).uniqueResult();
    return count.intValue();
  }
}