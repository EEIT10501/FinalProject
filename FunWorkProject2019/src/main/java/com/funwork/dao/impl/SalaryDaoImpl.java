package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.SalaryDao;
import com.funwork.model.Salary;

@Repository
public class SalaryDaoImpl implements SalaryDao {

	@Autowired
	SessionFactory factory;

	public SalaryDaoImpl() {

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Salary> getAllSalarys() {
		String hql = "FROM Salary";
		Session session = null;
		List<Salary> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}
