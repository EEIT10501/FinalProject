package com.funwork.service.impl;

import com.funwork.dao.CompanyDao;
import com.funwork.dao.NotificationDao;
import com.funwork.model.Company;
import com.funwork.model.Notification;
import com.funwork.model.User;
import com.funwork.service.CompanyService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CompanyServiceImp implements CompanyService {

  @Autowired
  CompanyDao companyDao;
  @Autowired
  NotificationDao notificationDao;

  @Override
  public Company findByPrimaryKey(int key) {
    return companyDao.findByPrimaryKey(key);
  }

  @Override
  public void saveCompany(Company company) {
    company.setSubmitTime(new Timestamp(System.currentTimeMillis()));
    companyDao.saveCompany(company);
  }

  @Override
  public void updateCompanyById(int id, Company company) {
    companyDao.updateCompanyById(id, company);
  }

  @Override
  public void deleteCompanyByPrimaryKey(int key) {
    companyDao.deleteCompanyByPrimaryKey(key);
  }

  @Override
  public List<Company> findAllCompanys() {
    return companyDao.findAllCompanys();
  }
  
  @Override
  public List<Company> findAllCompanys(String reviewStatus) {
    return companyDao.getAllCompanysByReviewStatus(reviewStatus);
  }

  @Override
  public void deleteAllCompanys() {
    companyDao.deleteAllCompanys();
  }

  @Override
  public boolean isCompanyExist(Company company) {
    return companyDao.isCompanyExist(company);
  }

  @Override
  public Company findByName(String name) {
    return companyDao.findByName(name);
  }

  @Override
  public List<Company> findAllCompanyByUserId(Integer userId) {
    return companyDao.findAllCompanyByUserId(userId);
  }

  @Override
  public List<String> findAllCompanyByUser(User user) {
    return companyDao.findAllCompanyByUser(user);
  }

  @Override
  public Company findCompanyByUserAndName(Integer userId, String companyName) {
    return companyDao.findCompanyByUserAndName(userId, companyName);
  }

  @Override
  public List<Company> getCompanyReviewList() {
    return companyDao.getCompanyReviewList();
  }

  @Override
  public void companyReviewPass(Integer companyId) {

    Company company = companyDao.findByPrimaryKey(companyId);
    company.setReviewStatus("已通過");
    company.setReviewTime(new Timestamp(System.currentTimeMillis()));
    companyDao.updateCompany(company);

    Notification notification = new Notification();
    notification.setContent("您的公司(" + company.getName() + ")已通過審核");
    notification.setTime(new Timestamp(System.currentTimeMillis()));
    notification.setType(3);
    notification.setUser(company.getUser());
    notificationDao.insertNotification(notification);

  }

  @Override
  public void companyReviewPassReviewFail(Integer companyId, String failReason) {
    Company company = companyDao.findByPrimaryKey(companyId);
    company.setReviewStatus("審核失敗");
    company.setReviewTime(new Timestamp(System.currentTimeMillis()));
    company.setFailReason(failReason);
    companyDao.updateCompany(company);
    
    Notification notification = new Notification();
    notification.setContent("您的公司(" + company.getName() + ")審核失敗");
    notification.setTime(new Timestamp(System.currentTimeMillis()));
    notification.setType(3);
    notification.setUser(company.getUser());
    notificationDao.insertNotification(notification);

  }

  @Override
  public List<Company> getReviewHistory() {
    // TODO Auto-generated method stub
    return companyDao.getReviewHistory();
  }

}
