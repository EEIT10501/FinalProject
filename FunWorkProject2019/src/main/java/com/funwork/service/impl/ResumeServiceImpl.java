package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ResumeDao;
import com.funwork.model.Resume;
import com.funwork.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeDao dao;

	public ResumeServiceImpl() {
	}

	@Transactional
	@Override
	public List<Resume> getAllResumes() {
		return dao.getAllResumes();
	}

}
