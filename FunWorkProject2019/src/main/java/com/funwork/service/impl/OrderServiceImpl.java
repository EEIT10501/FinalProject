package com.funwork.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.OrderDao;
import com.funwork.model.Order;
import com.funwork.model.Product;
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

  @Transactional
  @Override
  public List<Product> getAllProducts() {
    return dao.getAllProducts();
  }

  @Transactional
  @Override
  public List<Order> getOrderByUser(Integer userId) {
    return dao.getOrderByUser(userId);
  }

  @Transactional
  @Override
  public Order insertOrder(Order order) {
    dao.insertOrder(order);
    return order;
  }

  @Transactional
  @Override
  public Order getOrderByTradeNo(String orderTradeNo) {
    return dao.getOrderByTradeNo(orderTradeNo);
  }

  @Transactional
  @Override
  public String getOrderByMouth() {
    String JsonStr = dao.getOrderByMouth();
    System.out.println("JsonStr = " + JsonStr);
    return JsonStr;
  }

}
