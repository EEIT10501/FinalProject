package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.ProductDao;
import com.funwork.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory factory;

	public ProductDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		String hql = "FROM Product";
		Session session = null;
		List<Product> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}
