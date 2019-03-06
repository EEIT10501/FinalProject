package com.funwork.dao;

import java.util.List;

import com.funwork.model.Company;

public interface CompanyDao {
	Company findByPrimaryKey(int key);
	
	void saveCompany(Company company);

	void updateCompany(Company company);

	void deleteCompanyByPrimaryKey(int key);

	List<Company> findAllCompanys();

	void deleteAllCompanys();

	public boolean isCompanyExist(Company company);

	Company findByName(String name);

	List<Company> getAllCompanysByReviewStatus(String reviewStatus);
}
