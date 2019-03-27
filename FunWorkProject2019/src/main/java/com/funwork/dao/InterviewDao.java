package com.funwork.dao;

import com.funwork.model.Interview;
import java.util.List;

public interface InterviewDao {
  Interview findByPrimaryKey(int key);

  void saveInterview(Interview interview);

  void updateInterview(Interview interview);

  List<Interview> findByApplicationIdAndTimeProcessing(int key);

  List<Interview> findByApplicationIdAndTimeCompleted(int key);

  List<Interview> findByApplicationIdAndTimeExpired(int key);

  List<Interview> findInterviewByAdmit(Integer jobId);

  Interview findByAdmitJobUserName(Integer jobId, String userName);

  List<Interview> findInterviewsByJobOwner(Integer userId);
}