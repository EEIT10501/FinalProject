package com.funwork.service.impl;

import com.funwork.dao.ExperienceDao;
import com.funwork.dao.ResumeDao;
import com.funwork.dao.UserDao;
import com.funwork.model.Experience;
import com.funwork.model.Resume;
import com.funwork.model.User;
import com.funwork.service.ResumeService;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class ResumeServiceImpl implements ResumeService {

  public static final Logger logger = Logger.getLogger("com.funwork");
  @Autowired
  ResumeDao resumeDao;
  @Autowired
  UserDao userDao;
  @Autowired
  ExperienceDao experienceDao;

  @Override
  public List<Resume> getAllResumes() {
    return resumeDao.getAllResumes();
  }

  @Override
  public Resume getResumeByUserId(Integer userId) {
    return resumeDao.getResumeByUserId(userId);
  }

  @Override
  public User getUserById(int userId) {
    return resumeDao.getUserById(userId);
  }


  @Override
  public Resume getResumeById(Integer resumeId) {
    return resumeDao.getResumeById(resumeId);
  }


  @Override
  public void addResume(Resume resume, Integer userId) {
    
    User user = userDao.getUserById(userId);
    resume.setUser(user);

    MultipartFile profileImage = resume.getProfilePart();
    String originalFilename = profileImage.getOriginalFilename();
    resume.setFileName(originalFilename);
    if (!profileImage.isEmpty()) {
      try {
        byte[] b = profileImage.getBytes();
        Blob blob = new SerialBlob(b);
        resume.setProfilePic(blob);
        resumeDao.addResume(resume);
      } catch (Exception e) {
        logger.log(Level.WARNING, "檔案上傳發生異常: {0}", e.getMessage());
      }
    } else {
      resumeDao.addResumeWithoutPictrue(resume);
    }   
  }
}