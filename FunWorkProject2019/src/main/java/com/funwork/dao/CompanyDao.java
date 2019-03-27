package com.funwork.dao;

import com.funwork.model.Company;
import com.funwork.model.User;
import java.util.List;

public interface CompanyDao {
  Company findByPrimaryKey(int key);

  void saveCompany(Company company);

  void updateCompanyById(int id, Company company);

  List<Company> findAllCompanyByUserId(Integer userId);

  List<String> findAllCompanyByUser(User user);

  Company findCompanyByUserAndName(Integer userId, String companyName);

  List<Company> getCompanyReviewList();

  void updateCompany(Company company);

  List<Company> getReviewHistory();

  boolean isTaxIdExist(String taxId);
}