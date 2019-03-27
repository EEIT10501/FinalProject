package com.funwork.dao;

import com.funwork.model.Complaint;
import java.util.List;

public interface ComplaintDao {

  List<Complaint> getComplaintProcessList();

  Complaint getComplaintById(Integer cpId);

  void processComplaint(Complaint cp);

  void insertCp(Complaint cp);

  List<Complaint> getComplaintHistoryList();
}