package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.ProductDao;
import com.funwork.model.Product;
import com.funwork.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Transactional
	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}

}
