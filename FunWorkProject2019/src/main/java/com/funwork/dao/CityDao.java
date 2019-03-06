package com.funwork.dao;

import java.util.List;

import com.funwork.model.City;

public interface CityDao {

	List<City> getAllCitys();

	List<City> getCityName(Integer cityId);

}