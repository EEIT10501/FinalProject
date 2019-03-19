package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ExperienceDao;
import com.funwork.dao.ResumeDao;
import com.funwork.dao.UserDao;
import com.funwork.model.Experience;
import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeDao resumeDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ExperienceDao experienceDao;
	public ResumeServiceImpl() {
	}

	@Transactional
	@Override
	public List<Resume> getAllResumes() {
	return resumeDao.getAllResumes();
	}

	@Transactional
	@Override
	public Resume getResumeByUserId(Integer userId) {
	return resumeDao.getResumeByUserId(userId);
	}

	@Transactional
	@Override
	//從使用者找到履歷
	public void addResume(Resume resume, Integer userId) {
		User user = userDao.findByPrimaryKey(userId);//取得userID
		resume.setUser(user);//設定履歷對應的userID	
		resumeDao.addResume(resume); 
//		Experience experience = new Experience();
//		experience.setResume(rs);//設這行才能關聯到resume的id
//		experience.setPosition(position);
//		experience.setCompany(company);
//		experience.setTerm(term);
//		experienceDao.insertExperience(experience);
//		resumeDao.getResumeByUserId(userId);
//		Experience ex = new Experience();
//		ex.setResume(resume);		
	}

	@Transactional
	@Override
	public User getUserById(int userId) {

		return resumeDao.getUserById(userId);
	}



	




}
