package com.funwork.service;

import java.util.List;

import com.funwork.model.Company;
import com.funwork.model.User;

public interface CompanyService {
	
	Company findByPrimaryKey(int key);

	void saveCompany(Company company);

	void deleteCompanyByPrimaryKey(int key);

	List<Company> findAllCompanys();

	void deleteAllCompanys();

	public boolean isCompanyExist(Company company);

	Company findByName(String name);
	
	List<Company> findAllCompanys(String reviewStatus);

	void updateCompanyById(int id, Company company);
	
	public List<Company> findAllCompanyByUserId(Integer userId);

	List<String> findAllCompanyByUser(User user);
	
	Company findCompanyByUserAndName(Integer userId, String companyName);
}
