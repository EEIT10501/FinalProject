package com.funwork.dao.impl;

import com.funwork.dao.JobDao;
import com.funwork.model.Job;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class JobDaoImpl implements JobDao {

  @Autowired
  SessionFactory factory;

  @Override
  @SuppressWarnings("unchecked")
  public List<Job> getAllJobs() {
    String hql = "FROM Job";
    Session session = null;
    List<Job> list = new ArrayList<>();
    session = factory.getCurrentSession();
    list = session.createQuery(hql).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobReviewList() {
    String hql = "FROM Job WHERE reviewStatus = '待審核' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobPassed() {
    String hql = "FROM Job WHERE reviewStatus = '發布中' ORDER BY submitTime ASC";
    List<Job> list = new ArrayList<>();
    Session session = factory.getCurrentSession();
    list = session.createQuery(hql).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobByCityName(Integer cityId) {
    String hql = "FROM Job WHERE Fk_City_Id = :cityId AND isFilled = false ORDER BY submitTime ASC";
    List<Job> list = new ArrayList<>();
    Session session = factory.getCurrentSession();
    Query query = session.createQuery(hql).setParameter("cityId", cityId);
    list = query.getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobByCityArea(Integer cityId) {
    String hql = "";
    if (cityId <= 12) {
      hql = "FROM Job WHERE Fk_City_Id <= 12 AND isFilled = false ORDER BY submitTime ASC";
    }
    if (cityId >= 13 && cityId < 42) {
      hql = "FROM Job WHERE Fk_City_Id BETWEEN 13 AND 41 AND isFilled = false ORDER BY submitTime ASC";
    }
    List<Job> list = new ArrayList<>();
    Session session = factory.getCurrentSession();
    list = session.createQuery(hql).getResultList();
    return list;
  }

  @Override
  public Job getJobById(Integer jobId) {
    Session session = factory.getCurrentSession();
    return session.get(Job.class, jobId);
  }

  @Override
  public Job updateJob(Job job) {
    Session session = factory.getCurrentSession();
    session.update(job);
    return job;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Job> findJobByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Job WHERE jobOwner.userId = :userId ORDER BY submitTime ASC";
    List<Job> list = session.createQuery(hql).setParameter("userId", userId).getResultList();
    return list;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Job> findJobByUserIdNJobStatus(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Job WHERE jobOwner.userId = :userId and reviewStatus =:reviewStatus";
    List<Job> list = session.createQuery(hql).setParameter("userId", userId).setParameter("reviewStatus", "發布中")
        .getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getCorrectJobs() {
    String hql = "FROM Job WHERE reviewStatus = '發布中' AND isFilled = false ORDER BY submitTime ASC";
    String hql2 = "FROM Job WHERE reviewStatus = '發布中' AND isFilled = false AND isExposure = false ORDER BY submitTime ASC";
    List<Job> list = new ArrayList<>();
    Session session = factory.getCurrentSession();
    list = session.createQuery(hql).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getReviewHistory() {
    String hql = "FROM Job WHERE reviewStatus != '待審核' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public Job insertJob(Job job) {
    Session session = factory.getCurrentSession();
    session.save(job);
    return job;
  }

  @Override
  public int getJobPostedCount(Integer userId) {
    Long count;
    Session session = factory.getCurrentSession();
    String hql = "SELECT count(*) FROM Job j WHERE j.jobOwner.userId = :userId "
        + "AND j.postEndDate >= :nowdate";
    count = (Long) session.createQuery(hql).setParameter("userId", userId)
        .setParameter("nowdate", new Date(System.currentTimeMillis())).uniqueResult();
    return count.intValue();
  }

}
