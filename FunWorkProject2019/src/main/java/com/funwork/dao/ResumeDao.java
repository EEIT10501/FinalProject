package com.funwork.dao;

import com.funwork.model.Resume;
import com.funwork.model.User;
import java.util.List;

public interface ResumeDao {

  List<Resume> getAllResumes();

  Resume getResumeByUserId(Integer userId);

  Resume addResume(Resume resume);

  User getUserById(int userId);

  Resume getResumeById(Integer resumeId);
}