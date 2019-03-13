package com.funwork.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		company.setLicensure(company.getLicensure());
		session.save(company);
	}
	

	@Override
	public void updateCompanyById(int id,Company company) {
		String hql = "UPDATE Company SET siteURL = :url, logo = :logo, coverPic = :coverPic, reviewStatus = :status WHERE companyId = :id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("url", company.getSiteURL()).
		setParameter("logo", company.getLogo()).
		setParameter("coverPic", company.getCoverPic()).
		setParameter("status", "公司完成建檔").
		setParameter("id", id).
		executeUpdate();
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
	public List<Company> getAllCompanysByReviewStatus(String reviewStatus) {
		System.out.println("parameter received in DaoImp is "+reviewStatus);
		Session session = factory.getCurrentSession();
		String hql = "FROM Company WHERE reviewStatus= :reviewStatus";
		List<Company> list=  session.createQuery(hql).setParameter("reviewStatus", reviewStatus).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAllCompanyByUserId(Integer userId){
		System.out.println("findAllCompanyByUserId() called");
		Session session = factory.getCurrentSession();
		String hql = "FROM Company WHERE user.userId = :userId ORDER BY companyId ASC";
		List<Company> list=  session.createQuery(hql).setParameter("userId", userId).getResultList();
		return list;
	}
	
}
