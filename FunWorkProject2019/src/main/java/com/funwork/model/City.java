package com.funwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

  private Integer cityId;
  private String cityName; // 行政區
  private String cityArea; // 台北市、新北市

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  @Column(nullable = false, columnDefinition = "nvarchar(255)")
  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  @Column(nullable = false, columnDefinition = "nvarchar(255)")
  public String getCityArea() {
    return cityArea;
  }

  public void setCityArea(String cityArea) {
    this.cityArea = cityArea;
  }

}
