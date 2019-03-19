package com.funwork.dao.impl;

import com.funwork.dao.ResumeDao;
import com.funwork.model.Resume;
import com.funwork.model.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDaoImpl implements ResumeDao {

  @Autowired
  SessionFactory factory;

  @Override
  @SuppressWarnings("unchecked")
  public List<Resume> getAllResumes() {
    String hql = "FROM Resume";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @Override
  public Resume getResumeByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Resume WHERE Fk_User_Id = :userId";
    if (session.createQuery(hql).setParameter("userId", userId).getResultList().size() != 0) {
      Resume resume = (Resume) session.createQuery(hql).setParameter("userId", userId).getSingleResult();
      return resume;
    } else {
      return null;
    }

  }

  @Override
  public Resume addResume(Resume resume) {
    Session session = factory.getCurrentSession();
    return (Resume)session.merge(resume);
  }

  @Override
  public User getUserById(int userId) {
    User ur = null;
    Session session = factory.getCurrentSession();
    ur = session.get(User.class, userId);
    return ur;
  }

  @Override
  public Resume getResumeById(Integer resumeId) {
    Session session = factory.getCurrentSession();
    return session.get(Resume.class, resumeId);
  }

}
