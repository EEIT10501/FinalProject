package com.funwork.service;

import com.funwork.model.Resume;

public interface ResumeService {
  Resume getResumeByUserId(Integer userId);

  void addResume(Resume resume, Integer userId);
}