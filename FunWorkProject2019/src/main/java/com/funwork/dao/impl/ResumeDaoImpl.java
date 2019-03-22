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

  @Override
  public void addResumeWithoutPictrue(Resume resume) {
    Session session = factory.getCurrentSession();
    Resume re = session.get(Resume.class, resume.getResumeId());
    re.setName(resume.getName());
    re.setGender(resume.getGender());
    re.setPhoneNum(resume.getPhoneNum());
    re.setBirth(resume.getBirth());
    re.setEducationLevel(resume.getEducationLevel());
    re.setType1(resume.getType1());
    re.setPosition1(resume.getPosition1());
    re.setTerm1(resume.getTerm1());
    re.setType2(resume.getType2());
    re.setPosition2(resume.getPosition2());
    re.setTerm2(resume.getTerm2());
    re.setSelfIntro(resume.getSelfIntro());
  }

}
