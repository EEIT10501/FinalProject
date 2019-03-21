package com.funwork.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funwork.dao.CityDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.service.CityService;

@Service
public class CityServiceImp implements CityService {
	
	@Autowired
	CityDao cityDao;

	@Transactional
	@Override
	public List<City> getAllCitys() {
		return cityDao.getAllCitys();
	}
	
	@Transactional
	@Override
	public List<City> getCityName(Integer cityId) {
		return cityDao.getCityName(cityId);
	}

	@Transactional
	@Override
	public List<String> getCityAreaList() {
		return cityDao.getCityAreaList();
	}

	@Transactional
	@Override
	public String getCityNameList(String cityArea) {
		return cityDao.getCityNameList(cityArea);
	}

	@Transactional
	@Override
	public City getCityByCityName(String cityName) {
		return getCityByCityName(cityName);
	}

	@Override
	public Job loadCityNameNArea(Job job) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
