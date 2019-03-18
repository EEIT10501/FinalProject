package com.funwork.service;

import com.funwork.model.Company;
import com.funwork.model.User;
import java.util.List;

public interface CompanyService {

  Company findByPrimaryKey(int key);

  void saveCompany(Company company);

  void deleteCompanyByPrimaryKey(int key);

  List<Company> findAllCompanys();

  List<Company> findAllCompanys(String reviewStatus);

  void deleteAllCompanys();

  public boolean isCompanyExist(Company company);

  Company findByName(String name);

  void updateCompanyById(int id, Company company);

  public List<Company> findAllCompanyByUserId(Integer userId);

  List<String> findAllCompanyByUser(User user);

  Company findCompanyByUserAndName(Integer userId, String companyName);

  List<Company> getCompanyReviewList();

  void companyReviewPass(Integer companyId);

  void companyReviewPassReviewFail(Integer companyId, String failReason);
}
