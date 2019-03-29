package com.funwork.service;

import com.funwork.model.Interview;
import java.util.List;

public interface InterviewService {
  Interview findByPrimaryKey(int key);

  Integer saveInterview(String interType, String interComment, 
      String interPlace, String interTime, Integer apId);

  void updateInterviewResult(String interviewResult, int key);

  void updateInterview(Interview interview);

  List<Interview> findByApplicationIdAndTimeProcessing(int key);

  List<Interview> findByApplicationIdAndTimeCompleted(int key);

  List<Interview> findByApplicationIdAndTimeExpired(int key);

  List<Interview> findInterviewByAdmit(Integer jobId);

  Interview findByAdmitJobUserName(Integer jobId, String userName);

  public List<Interview> findInterviewsByJobOwner(Integer userId);
}