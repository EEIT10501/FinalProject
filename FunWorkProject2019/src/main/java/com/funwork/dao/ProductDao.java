package com.funwork.dao;

import com.funwork.model.Product;
import java.util.List;

public interface ProductDao {
  List<Product> getAllProducts();
}