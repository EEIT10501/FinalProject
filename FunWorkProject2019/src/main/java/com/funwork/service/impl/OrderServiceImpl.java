package com.funwork.service.impl;

import com.funwork.dao.OrderDao;
import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderDao dao;

  @Override
  public List<Order> getAllOrders() {
    return dao.getAllOrders();
  }

  @Override
  public List<Product> getAllProducts() {
    return dao.getAllProducts();
  }

  @Override
  public Order insertOrder(Order order) {
    dao.insertOrder(order);
    return order;
  }

  @Override
  public Order getOrderByTradeNo(String orderTradeNo) {
    return dao.getOrderByTradeNo(orderTradeNo);
  }

  @Override
  public String getOrderByMouth() {
    return dao.getOrderByMouth();
  }

  @Override
  public List<Order> getOrdersById(Integer userId) {
    return dao.getOrdersById(userId);
  }
}