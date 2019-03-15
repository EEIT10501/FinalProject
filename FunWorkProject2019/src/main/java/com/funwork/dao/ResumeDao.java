package com.funwork.dao;

import java.util.List;

import com.funwork.model.Resume;
import com.funwork.model.User;

public interface ResumeDao {

	List<Resume> getAllResumes();

	Resume getResumeByUserId(Integer userId);
	
//	void addResume(Resume resume);
	
//	User getUserById(int userId); 
}
