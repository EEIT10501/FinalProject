package com.funwork.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.funwork.model.Job;

public interface JobService {

	List<Job> getAllJobs();

}