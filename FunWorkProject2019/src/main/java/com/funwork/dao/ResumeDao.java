package com.funwork.dao;

import com.funwork.model.Resume;

public interface ResumeDao {
  Resume getResumeByUserId(Integer userId);

  void addResume(Resume resume);

  void addResumeWithoutPictrue(Resume resume);
}