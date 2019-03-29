package com.funwork.dao.impl;

import com.funwork.dao.ApplicationDao;
import com.funwork.model.Application;
import com.funwork.model.Job;
import com.funwork.model.User;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDaoImp implements ApplicationDao {
  private static final String USERID = "userId";
  @Autowired
  SessionFactory factory;

  @Override
  public Application findByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    return session.get(Application.class, key);
  }

  @Override
  public void insertApplication(Integer userId, Integer jobId, String question) {
    Session session = factory.getCurrentSession();
    Application application = new Application();
    User user = session.get(User.class, userId);
    Job job = session.get(Job.class, jobId);
    application.setApplicationTime(new Timestamp(System.currentTimeMillis()));
    application.setUser(user);
    application.setJob(job);
    application.setAnswer(question);
    application.setAppliedStatus("待回應");
    session.save(application);
  }

  @Override
  public void updateApplication(Application application) {
    Session session = factory.getCurrentSession();
    Application app = session.get(Application.class, application.getApplicationId());
    app.setAppliedStatus("已邀約");
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Application> findAllApplicantByJobId(Job job) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Application WHERE job = :job";
    return session.createQuery(hql).setParameter("job", job).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Application> getApplicationByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Application a WHERE (a.user.userId = :userId "
        + "OR a.job.jobOwner.userId = :userId2) and a.latestMsg IS NOT NULL " 
        + "ORDER BY a.latestMsgTime DESC";

    return session.createQuery(hql).setParameter(USERID, userId)
        .setParameter("userId2", userId).getResultList();
  }

  @Override
  public void updateLatestMsg(Integer apId, String msg) {
    Session session = factory.getCurrentSession();
    Application ap = session.get(Application.class, apId);
    ap.setLatestMsg(msg);
    ap.setLatestMsgTime(new Timestamp(System.currentTimeMillis()));
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Application> getApplicationByUserIdByTime(Integer userId) {

    Session session = factory.getCurrentSession();
    String hql = "FROM Application a WHERE (a.user.userId = :userId)" 
        + "ORDER BY a.applicationTime ";
    return session.createQuery(hql).setParameter(USERID, userId).getResultList();
  }

  @Override
  public void refuseUser(Integer apId) {
    Session session = factory.getCurrentSession();
    Application ap = session.get(Application.class, apId);
    ap.setAppliedStatus("已婉拒");
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Application> findByJobId(Integer jobId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Application a WHERE a.job.jobId = :jobId";

    List<Application> list = session.createQuery(hql).setParameter("jobId", jobId).getResultList();
    if (!list.isEmpty()) {
      return list;
    } else {
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public Application isApplicantExist(Integer userId, Integer jobId) {
    Application application = null;
    Session session = factory.getCurrentSession();
    String hql = "FROM Application a WHERE a.job.jobId = :jobId AND a.user.userId = :userId";
    List<Application> list = session.createQuery(hql).setParameter("jobId", jobId)
        .setParameter(USERID, userId)
        .getResultList();
    if (!list.isEmpty()) {
      application = list.get(0);
    }
    return application;
  }
}