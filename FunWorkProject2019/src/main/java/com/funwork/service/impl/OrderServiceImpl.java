package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.OrderDao;
import com.funwork.model.Order;
import com.funwork.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao dao;

	@Transactional
	@Override
	public List<Order> getAllOrders() {
		return dao.getAllOrders();
	}

}
