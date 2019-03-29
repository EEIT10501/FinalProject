package com.funwork.dao.impl;

import com.funwork.dao.CompanyDao;
import com.funwork.model.Company;
import com.funwork.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImp implements CompanyDao {

  @Autowired
  SessionFactory factory;



  @Override
  public Company findByPrimaryKey(int key) {
    Session session = factory.getCurrentSession();
    return session.get(Company.class, key);
  }


	@Override
	public void saveCompany(Company company) {
		Session session = factory.getCurrentSession();
		company.setLicensure(company.getLicensure());
		session.save(company);
	}


  @Override
  public void updateCompanyById(int id, Company company) {
    String hql = "UPDATE Company SET siteUrl = :url, logo = :logo, "
        + "coverPic = :coverPic, reviewStatus = :status WHERE companyId = :id";
    Session session = factory.getCurrentSession();
    session.createQuery(hql).setParameter("url", company.getSiteUrl())
    .setParameter("logo", company.getLogo()).setParameter("coverPic", company.getCoverPic())
    .setParameter("status", "公司完成建檔").setParameter("id", id).executeUpdate();
  }



  @SuppressWarnings("unchecked")
  @Override
  public List<Company> findAllCompanyByUserId(Integer userId) {
    Session session = factory.getCurrentSession();
    String hql = "FROM Company WHERE user.userId = :userId ORDER BY companyId ASC";
    return session.createQuery(hql).setParameter("userId", userId).getResultList();
  }



  @SuppressWarnings("unchecked")
  @Override
  public List<String> findAllCompanyByUser(User user) {
    String hql = "SELECT DISTINCT name FROM Company c WHERE c.user = :user "
        + "AND (c.reviewStatus = '審核通過' OR c.reviewStatus = '公司完成建檔')";
    Session session = factory.getCurrentSession();
    return session.createQuery(hql).setParameter("user", user).getResultList();
  }


	@SuppressWarnings("unchecked")
	@Override
	public Company findCompanyByUserAndName(Integer userId, String companyName) {
		Company company = null;
		String hql = "FROM Company c  WHERE c.user.userId = :userId AND c.name = :companyName";
		Session session = factory.getCurrentSession();
		List<Company> list = session.createQuery(hql).setParameter("userId", userId)
				.setParameter("companyName", companyName).getResultList();
		if (!list.isEmpty()) {
			company = list.get(0);
		}
		return company;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyReviewList() {
		String hql = "FROM Company WHERE reviewStatus = '待審核' ORDER BY submitTime ASC";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public void updateCompany(Company company) {
		Session session = factory.getCurrentSession();
		session.update(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getReviewHistory() {
		String hql = "FROM Company WHERE reviewStatus != '待審核' ORDER BY submitTime ASC";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}


  @SuppressWarnings("unchecked")
  @Override
  public boolean isTaxIdExist(String taxId) {
    Boolean isExist = false;
    String hql = "FROM Company WHERE taxId = :taxId";
    Session session = factory.getCurrentSession();
    List<Company> list = session.createQuery(hql).setParameter("taxId", taxId).getResultList();
    if (!list.isEmpty()) {
      isExist = true;
    }
    return isExist;
  }
}