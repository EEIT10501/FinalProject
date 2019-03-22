package com.funwork.dao.impl;

import com.funwork.dao.ResumeDao;
import com.funwork.model.Resume;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDaoImpl implements ResumeDao {
  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public Resume getResumeByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Resume WHERE Fk_User_Id = :userId";
    Resume resume = null;
    List<Resume> list = session.createQuery(hql).setParameter("userId", userId).getResultList();
    if (!list.isEmpty()) {
      resume = list.get(0);
    }
    return resume;
  }

  @Override
  public void addResume(Resume resume) {
    Session session = factory.getCurrentSession();
    session.merge(resume);
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