package com.funwork.model;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"Order\"")
public class Order {

  private Integer orderId;
  private Timestamp orderTime;
  private Integer status;
  private Integer price;
  private String orderTradeNo;
  private User user;
  private Product product;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Timestamp getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Timestamp orderTime) {
    this.orderTime = orderTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getOrderTradeNo() {
    return orderTradeNo;
  }

  public void setOrderTradeNo(String orderTradeNo) {
    this.orderTradeNo = orderTradeNo;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_User_Id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_Product_Id")
  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

}
