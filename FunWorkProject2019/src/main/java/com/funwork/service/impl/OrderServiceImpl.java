package com.funwork.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.OrderDao;
import com.funwork.dao.UserDao;
import com.funwork.model.Order;
import com.funwork.model.Product;
import com.funwork.model.User;
import com.funwork.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderDao dao;
  
  @Autowired
  UserDao userdao;

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
  public Order insertOrder(Order order,Integer rtncode) {
    dao.insertOrder(order);
    if (rtncode==1) {
        User user = order.getUser();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        if (order.getProduct().getProductId() == 1) {
          cal.add(Calendar.DATE, 30);
        } else if (order.getProduct().getProductId() == 2) {
          cal.add(Calendar.DATE, 180);
          user.setExposureLimit(2);
        } else if (order.getProduct().getProductId() == 3) {
          cal.add(Calendar.DATE, 365);
          user.setExposureLimit(5);
        }

        if (user.getVipEndDate() == null) {
          user.setVipEndDate(java.sql.Date.valueOf(sdf.format(cal.getTime())));
        } else if (user.getVipEndDate().before(java.sql.Date.valueOf(sdf.format(date)))) {
          user.setVipEndDate(java.sql.Date.valueOf(sdf.format(cal.getTime())));
        } else {
          cal.setTime(user.getVipEndDate());
          if (order.getProduct().getProductId() == 1) {
            cal.add(Calendar.DATE, 30);
            user.setVipEndDate(java.sql.Date.valueOf(sdf.format(cal.getTime())));
          } else if (order.getProduct().getProductId() == 2) {
            cal.add(Calendar.DATE, 180);
            user.setVipEndDate(java.sql.Date.valueOf(sdf.format(cal.getTime())));
            user.setExposureLimit(2);
          } else if (order.getProduct().getProductId() == 3) {
            cal.add(Calendar.DATE, 365);
            user.setVipEndDate(java.sql.Date.valueOf(sdf.format(cal.getTime())));
            user.setExposureLimit(5);
          }

        }
        user.setJobPostLimit(99);
        user.setJobPostPeriod(365);
        user.setMebershipLevel(2);
        userdao.updateUser(user);
      }
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