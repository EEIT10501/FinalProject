package com.funwork.service;

import java.util.List;

import com.funwork.model.City;
import com.funwork.model.Job;

public interface CityService {

	List<City> getAllCitys();

	List<City> getCityName(Integer cityId);

	List<String> getCityAreaList();

	String getCityNameList(String cityArea);
	
	City getCityByCityName(String cityName);

	Job loadCityNameNArea(Job job);

}