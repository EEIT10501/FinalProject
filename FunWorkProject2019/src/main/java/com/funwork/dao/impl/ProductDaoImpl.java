package com.funwork.dao.impl;

import com.funwork.dao.ProductDao;
import com.funwork.model.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

  @Autowired
  SessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Product> getAllProducts() {
    String hql = "FROM Product";
    Session session = null;
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

}
