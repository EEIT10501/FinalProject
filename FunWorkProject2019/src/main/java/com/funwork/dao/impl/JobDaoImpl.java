package com.funwork.dao.impl;

import com.funwork.dao.JobDao;
import com.funwork.model.Job;
import com.google.gson.Gson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JobDaoImpl implements JobDao {
  private static final String USERID = "userId";
  private static final String HQL1 = "AND reviewStatus = '發布中' ORDER BY submitTime ASC";
  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobReviewList() {
    String hql = "FROM Job WHERE reviewStatus = '待審核' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobByCityName(Integer cityId) {
    String hql = "FROM Job WHERE Fk_City_Id = :cityId AND isFilled = false" + HQL1;
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter("cityId", cityId).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobByCityArea(Integer cityId) {
    String hql = "";
    if (cityId <= 12) {
      hql = "FROM Job WHERE Fk_City_Id <= 12 AND isFilled = false " + HQL1;
    }
    if (cityId >= 13 && cityId < 42) {
      hql = "FROM Job WHERE Fk_City_Id BETWEEN 13 AND 41 AND isFilled = false " + HQL1;
    }
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public Job getJobById(Integer jobId) {
    Session session = factory.getCurrentSession();
    return session.get(Job.class, jobId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Job> findJobByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Job WHERE jobOwner.userId = :userId ORDER BY submitTime ASC";
    return session.createQuery(hql).setParameter(USERID, userId).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getCorrectJobs() {
    String hql = "FROM Job WHERE reviewStatus = '發布中' AND isFilled = false ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getReviewHistory() {
    String hql = "FROM Job WHERE reviewStatus != '待審核' ORDER BY submitTime ASC";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public void insertJob(Job job) {
    Session session = factory.getCurrentSession();
    session.save(job);
  }

  @Override
  public int getJobPostedCount(Integer userId) {
    Long count;
    Session session = factory.getCurrentSession();
    String hql = "SELECT count(*) FROM Job j WHERE j.jobOwner.userId = :userId " + "AND j.postEndDate >= :nowdate "
        + "AND j.reviewStatus != :reviewFailed";
//		"AND j.reviewStatus != :pendingReview";
    count = (Long) session.createQuery(hql).setParameter("userId", userId)
        .setParameter("nowdate", new Date(System.currentTimeMillis())).setParameter("reviewFailed", "審核失敗")
        .uniqueResult();
//				.setParameter("pendingReview", "待審核").uniqueResult();
    return count.intValue();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Job> getJobsBySearchStr(String searchStr) {
    String hql = "FROM Job j WHERE j.title like :searchStr";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter("searchStr", "%" + searchStr + "%").getResultList();
  }

  @Override
  public Integer getJobExposureCount(Integer userId) {
    Long count;
    Session session = factory.getCurrentSession();
    String hql = "SELECT count(*) FROM Job j WHERE j.jobOwner.userId = :userId "
        + "AND j.postEndDate >= :nowdate AND j.isExposure = true";
    count = (Long) session.createQuery(hql).setParameter("userId", userId)
        .setParameter("nowdate", new Date(System.currentTimeMillis())).uniqueResult();
    return count.intValue();
  }

  @Override
  public Job updateJob(Job job) {
    Session session = factory.getCurrentSession();
    session.update(job);
    return job;
  }

  @Override
  public Integer getAllJobPostingCount() {
    Long count;
    Session session = factory.getCurrentSession();
    String hql = "SELECT count(*) FROM Job j WHERE j.reviewStatus = '發布中'"
        + "AND j.postEndDate >= :nowdate AND j.isFilled = false";
    count = (Long) session.createQuery(hql).setParameter("nowdate", new Date(System.currentTimeMillis()))
        .uniqueResult();
    return count.intValue();
  }

  @Override
  public String getAllPostingJobTypeJson() {
    Session session = factory.getCurrentSession();
    String hql = "SELECT j.industry ,count(*) FROM Job j WHERE j.reviewStatus = '發布中'"
        + "AND j.postEndDate >= :nowdate AND j.isFilled = false GROUP BY j.industry";
    List<?> list = session.createQuery(hql).setParameter("nowdate", new Date(System.currentTimeMillis()))
        .getResultList();
    return new Gson().toJson(list);
  }

  @Override
  public void updateJobByExpired() {
    String hql = "UPDATE Job SET reviewStatus = '已截止' , isFilled = false , isExposure = false WHERE postEndDate < :todayDate";
    Session session = factory.getCurrentSession();

    java.util.Date date = new java.util.Date();
    session.createQuery(hql).setParameter("todayDate", date).executeUpdate();
  }

}
