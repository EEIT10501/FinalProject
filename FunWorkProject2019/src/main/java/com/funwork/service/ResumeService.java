package com.funwork.service;

import com.funwork.model.Resume;
import com.funwork.model.User;
import java.util.List;

public interface ResumeService {

  List<Resume> getAllResumes();

  Resume getResumeByUserId(Integer userId);

  User getUserById(int userId);

  Resume getResumeById(Integer resumeId);

  void addResume(Resume resume, Integer userId);
}