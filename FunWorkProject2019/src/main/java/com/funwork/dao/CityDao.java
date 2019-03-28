package com.funwork.dao;

import com.funwork.model.City;
import java.util.List;

public interface CityDao {

  List<City> getCityName(Integer cityId);

  String getCityNameList(String cityArea);

  City getCityByCityName(String cityName);

  City getCityByPk(Integer cityId);
}