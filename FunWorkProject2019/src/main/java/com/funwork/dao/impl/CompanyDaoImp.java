package com.funwork.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.CompanyDao;
import com.funwork.model.Company;

@Repository
public class CompanyDaoImp implements CompanyDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public Company findByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Company company= session.get(Company.class, key);
		return company;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company findByName(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Member WHERE name=:name";
		
		Company company = null;
		List<Company> list = session.createQuery(hql).setParameter(1,name).getResultList();
		if (!list.isEmpty()) {
			company= list.get(0);
		}
		return company;
	}

	@Override
	public void saveCompany(Company company) {
		Session session = factory.getCurrentSession();
		session.save(company);
	}
	

	@Override
	public void updateCompany(Company company) {
		Session session = factory.getCurrentSession();
		session.update(company);
	}

	@Override
	public void deleteCompanyByPrimaryKey(int key) {
		Session session = factory.getCurrentSession();
		Company company = new Company();
		company.setCompanyId(key);
		session.delete(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAllCompanys() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Company";
		List<Company> list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void deleteAllCompanys() {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM Company";
		session.createQuery(hql).executeUpdate();
	}

	@Override
	public boolean isCompanyExist(Company company) {
		boolean exist = false;
		Session session = factory.getCurrentSession();
		String hql = "FROM Company WHERE companyId= :companyId";
		try {
			session.createQuery(hql).setParameter(1, company.getCompanyId()).getResultList();
			exist = true;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return exist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAllCompanysByReviewStatus(String status) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Company WHERE reviewStatus= :status";
		List<Company> list = session.createQuery(hql).setParameter(1, status).getResultList();
		return list;
	}
	
}
