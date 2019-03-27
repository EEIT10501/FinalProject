package com.funwork.service;

import com.funwork.model.Company;
import com.funwork.model.User;
import java.util.List;

public interface CompanyService {
  Company findByPrimaryKey(int key);

  void saveCompany(Company company);

  void updateCompanyById(int id, Company company);

  public List<Company> findAllCompanyByUserId(Integer userId);

  List<String> findAllCompanyByUser(User user);

  List<Company> getCompanyReviewList();

  void companyReviewPass(Integer companyId);

  void companyReviewPassReviewFail(Integer companyId, String failReason);

  List<Company> getReviewHistory();

  boolean isTaxIdExist(String taxId);
}