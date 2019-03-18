package com.funwork.dao;

import com.funwork.model.Order;
import java.util.List;

public interface OrderDao {
  List<Order> getAllOrders();
}