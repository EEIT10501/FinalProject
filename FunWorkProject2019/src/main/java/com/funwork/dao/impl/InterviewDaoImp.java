package com.funwork.dao.impl;

import com.funwork.dao.InterviewDao;
import com.funwork.model.Interview;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InterviewDaoImp implements InterviewDao {
  private static final String FIND_INTER_BY_APPLICANT 
      = "FROM Interview i WHERE i.application.user.userId = :userId";
  private static final String USERID = "userId";
  @Autowired
  SessionFactory factory;

  @Override
  public Interview findByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    return session.get(Interview.class, key);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeProcessing(int key) {
    String hql = FIND_INTER_BY_APPLICANT 
        + " AND i.interviewTime >= (getdate()) ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter(USERID, key).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeCompleted(int key) {
    String hql = FIND_INTER_BY_APPLICANT + " AND i.interviewStatus <> '待回應' "
        + " AND i.interviewTime < (getdate()) ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter(USERID, key).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeExpired(int key) {
    String hql = FIND_INTER_BY_APPLICANT + " AND i.interviewStatus = '待回應' "
        + " AND i.interviewTime < (getdate()) ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter(USERID, key).getResultList();
  }

  @Override
  public void saveInterview(Interview interview) {
    Session session = factory.getCurrentSession();
    session.save(interview);
  }

  @Override
  public void updateInterview(Interview interview) {
    Session session = factory.getCurrentSession();
    session.update(interview);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findInterviewByAdmit(Integer jobId) {
    String hql = "FROM Interview i WHERE interviewType = '錄取' AND i.application.job.jobId = :jobId";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter("jobId", jobId).getResultList();
  }

  @Override
  public Interview findByAdmitJobUserName(Integer jobId, String userName) {
    String hql = "FROM Interview i WHERE interviewType = '錄取' "
        + "AND i.application.job.jobId = :jobId AND i.application.user.userName = :userName";
    Session session = factory.getCurrentSession();
    return (Interview) session.createQuery(hql)
        .setParameter("jobId", jobId).setParameter("userName", userName).getSingleResult();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findInterviewsByJobOwner(Integer userId) {
    String hql = "FROM Interview i WHERE i.application.job.jobOwner.userId = :userId";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter(USERID, userId).getResultList();
  }
}