package com.funwork.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.InterviewDao;
import com.funwork.model.Interview;
import com.funwork.model.Job;
import com.funwork.model.User;

@Repository
public class InterviewDaoImp implements InterviewDao {

  @Autowired
  SessionFactory factory;

  @Override
  public Interview findByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    Interview interview = session.get(Interview.class, key);
    return interview;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeProcessing(int key) {
    String hql = "FROM Interview i WHERE i.application.user.userId = :userId" + " and i.interviewTime >= (getdate())"
        + " ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql).setParameter("userId", key).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeCompleted(int key) {

//		String hql = "FROM Interview i WHERE i.application.user.userId = :userId"+
//				" and i.interviewStatus = '接受' "+
//				" and i.interviewStatus = '拒絕' "+
//				" and i.interviewTime < (getdate())";
    String hql = "FROM Interview i WHERE i.application.user.userId = :userId" + " and i.interviewStatus <> '待回應' "
        + " and i.interviewTime < (getdate())" + " ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql).setParameter("userId", key).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIdAndTimeExpired(int key) {
    String hql = "FROM Interview i WHERE i.application.user.userId = :userId" + " and i.interviewStatus = '待回應' "
        + " and i.interviewTime < (getdate())" + " ORDER BY i.interviewTime";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql).setParameter("userId", key).getResultList();
    return list;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findByApplicationIds(int key) {
    String hql = "FROM Interview i WHERE i.application.user.userId = :userId";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql).setParameter("userId", key).getResultList();
    return list;
  }

//	@SuppressWarnings({ "unchecked", "deprecation" })
//	@Override
//	public List<Schedule> getSchedulesByJobId(Integer jobId) {
//		String hql = "FROM Schedule where Fk_Job_Id = :jobId";
//		Session session = null;
//		List<Schedule> list = new ArrayList<>();
//		session = factory.getCurrentSession();
//		list = session.createQuery(hql).setParameter("jobId", jobId).getResultList();		
//			return list;
//	}

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

  @Override
  public void deleteInterviewByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    Interview interview = new Interview();
    interview.setInterviewId(key);
    session.delete(interview);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findAllInterviews() {
    Session session = factory.getCurrentSession();
    String hql = "FROM Interview";
    List<Interview> list = session.createQuery(hql).getResultList();
    return list;
  }

  @Override
  public void deleteAllInterviews() {
    Session session = factory.getCurrentSession();
    String hql = "DELETE FROM Interview";
    session.createQuery(hql).executeUpdate();
  }

  @Override
  public boolean isInterviewExist(Interview interview) {
    boolean exist = false;
    Session session = factory.getCurrentSession();
    String hql = "FROM Interview WHERE interviewId=:interviewId";
    try {
      session.createQuery(hql).setParameter(1, interview.getInterviewId()).getResultList();
      exist = true;
    } catch (NoResultException e) {
      e.printStackTrace();
    }
    return exist;
  }
  
  @Override
  public List<Interview> findInterviewByAdmit(Integer jobId) {
    String hql = "FROM Interview i WHERE interviewType = '錄取' AND i.application.job.jobId = :jobId";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql).setParameter("jobId",jobId).getResultList();
    return list;
  }
  
  @Override
  public Interview findByAdmit_Job_UserName(Integer jobId,String userName) {
    String hql = "FROM Interview i WHERE interviewType = '錄取' AND i.application.job.jobId = :jobId AND i.application.user.userName = :userName";
    Session session = factory.getCurrentSession();
    Interview interview = (Interview) session.createQuery(hql).setParameter("jobId",jobId).setParameter("userName",userName).getSingleResult();
    return interview;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findInterviewsByJobOwnerId(Job job) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Interview WHERE application.job.jobOwner.userId = :userId";
    List<Interview> list = session.createQuery(hql)
    		.setParameter("userId", job.getJobOwner().getUserId())
    		.getResultList();
    return list;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Interview> findInterviewsByJobOwner(User jobOwner) {
    String hql = "FROM Interview i WHERE i.application.job.jobOwner.userId = :userId";
    Session session = factory.getCurrentSession();
    List<Interview> list = session.createQuery(hql)
    		.setParameter("userId", jobOwner.getUserId()).getResultList();
    return list;
  }
  
}
