package com.funwork.service;

import java.util.List;

import com.funwork.model.Resume;
import com.funwork.model.User;

public interface ResumeService {

	List<Resume> getAllResumes();

	Resume getResumeByUserId(Integer userId);	
//	Resume getResumeByResumeId(Integer userId);

	User getUserById(int userId);

	void addResume(Resume resume, Integer userId);
	
	
	
}
