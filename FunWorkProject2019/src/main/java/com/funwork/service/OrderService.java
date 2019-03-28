package com.funwork.service;

import com.funwork.model.Order;
import com.funwork.model.Product;
import java.util.List;

public interface OrderService {

  List<Order> getAllOrders();

  List<Product> getAllProducts();

  Order insertOrder(Order order);

  Order getOrderByTradeNo(String orderTradeNo);

  String getOrderByMouth();

  List<Order> getOrdersById(Integer userId);
}
