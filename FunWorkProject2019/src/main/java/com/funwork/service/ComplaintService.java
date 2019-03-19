package com.funwork.service;

import com.funwork.model.Complaint;
import java.util.List;

public interface ComplaintService {
  List<Complaint> getAllComplaints();

  List<Complaint> getComplaintProcessList();

  Complaint getComplaintById(Integer cpId);

  Complaint processComplaint(Integer cpId, String closeReason, String isRemove, Integer jobId);

  void insertCp(String type, String content, Integer jobId);

  List<Complaint> getComplaintHistoryList();
}
