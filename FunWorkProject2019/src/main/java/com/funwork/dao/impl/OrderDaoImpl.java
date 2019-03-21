package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.OrderDao;
import com.funwork.model.Order;
import com.funwork.model.Product;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		String hql = "FROM Order";
		Session session = null;
		List<Order> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		String hql = "FROM Product";
		Session session = null;
		List<Product> list = new ArrayList<Product>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderByUser(Integer userId){
		String hql = "FROM Order WHERE user.userId = :userId";
		Session session = null;
		List<Order> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("userId", userId).getResultList();
		return list;
	}
	
	@Override
	public Order insertOrder(Order order) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(order);
		return order;	
	}
	
	@Override
	public Order getOrderByTradeNo(String orderTradeNo){
		String hql = "FROM Order WHERE orderTradeNo = :orderTradeNo";
		Session session = factory.getCurrentSession();
		Order order = (Order) session.createQuery(hql).setParameter("orderTradeNo", orderTradeNo).getSingleResult();
		return order;
	}

}
