package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.CompanyDao;
import com.funwork.model.Company;
import com.funwork.service.CompanyService;

@Service
public class CompanyServiceImp implements CompanyService {
	
	@Autowired
	CompanyDao dao;
	
	@Transactional
	@Override
	public Company findByPrimaryKey(int key) {
		return dao.findByPrimaryKey(key);
	}
	
	@Transactional
	@Override
	public void saveCompany(Company company) {
		dao.saveCompany(company);
	}
	
	@Transactional
	@Override
	public void updateCompany(Company company) {

	}

	@Transactional
	@Override
	public void deleteCompanyByPrimaryKey(int key) {
		dao.deleteCompanyByPrimaryKey(key);
	}

	@Transactional
	@Override
	public List<Company> findAllCompanys() {
		return dao.findAllCompanys();
	}

	@Transactional
	@Override
	public void deleteAllCompanys() {
		dao.deleteAllCompanys();
	}
	
	@Transactional
	@Override
	public boolean isCompanyExist(Company company) {
		return dao.isCompanyExist(company);
	}

	@Transactional
	@Override
	public Company findByName(String name) {
		return dao.findByName(name);
	}

	@Transactional
	@Override
	public List<Company> findAllCompanys(String reviewStatus) {
		System.out.println("parameter received in serviceImp is "+reviewStatus);
		return dao.getAllCompanysByReviewStatus(reviewStatus);
	}

}
