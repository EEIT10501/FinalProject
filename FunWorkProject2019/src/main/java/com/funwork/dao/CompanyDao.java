package com.funwork.dao;

import com.funwork.model.Company;
import com.funwork.model.User;
import java.util.List;

public interface CompanyDao {
  Company findByPrimaryKey(int key);

  void saveCompany(Company company);

  void deleteCompanyByPrimaryKey(int key);

  List<Company> findAllCompanys();

  void deleteAllCompanys();

  public boolean isCompanyExist(Company company);

  Company findByName(String name);

  List<Company> getAllCompanysByReviewStatus(String reviewStatus);

  void updateCompanyById(int id, Company company);

  List<Company> findAllCompanyByUserId(Integer userId);

  List<String> findAllCompanyByUser(User user);

  Company findCompanyByUserAndName(Integer userId, String companyName);
  
  List<Company> getCompanyReviewList();

  void updateCompany(Company company);

  List<Company> getReviewHistory();
  
  Company getCompanyByUser(User user);
}
