package com.funwork.service;

import java.util.List;
import java.util.Map;

import com.funwork.model.Order;
import com.funwork.model.Product;

public interface OrderService {

  List<Order> getAllOrders();

  List<Product> getAllProducts();

  List<Order> getOrderByUser(Integer userId);

  Order insertOrder(Order order);

  Order getOrderByTradeNo(String orderTradeNo);
  
  String getOrderByMouth();
}
