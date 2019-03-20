package com.funwork.service;

import java.util.List;

import com.funwork.model.Order;
import com.funwork.model.Product;

public interface OrderService {
	
	List<Order> getAllOrders();
	
	List<Product> getAllProducts();
}
