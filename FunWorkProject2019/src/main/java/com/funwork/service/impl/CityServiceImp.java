package com.funwork.service.impl;

import com.funwork.dao.CityDao;
import com.funwork.model.City;
import com.funwork.service.CityService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CityServiceImp implements CityService {
  @Autowired
  CityDao cityDao;

  @Override
  public City getCityByCityName(String cityName) {
    return getCityByCityName(cityName);
  }
}