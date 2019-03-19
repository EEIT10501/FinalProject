package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.CityDao;
import com.funwork.model.City;
import com.google.gson.Gson;

@Repository
public class CityDaoImpl implements CityDao {

	@Autowired
	SessionFactory factory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.funwork.dao.impl.CityDao#getAllCiys()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<City> getAllCitys() {
		String hql = "FROM City";
		Session session = null;
		List<City> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.funwork.dao.impl.CityDao#getAllCiyArea(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<City> getCityName(Integer cityId) {
		String hql = "";
		if (cityId <= 12) {
			hql = "FROM City WHERE cityId <= 12";
		}
		if (cityId >= 13 && cityId < 42) {
			hql = "FROM City WHERE cityId BETWEEN 13 AND 41";
		}

		Session session = null;
		List<City> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCityAreaList() {
		String hql = "SELECT DISTINCT cityArea FROM City";
		Session session = factory.getCurrentSession();
		List<String> list = session.createQuery(hql).getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCityNameList(String cityArea) {
		String hql = "SELECT DISTINCT cityName FROM City WHERE cityArea = :cityArea";
		Session session = factory.getCurrentSession();
		List<String> list = session.createQuery(hql).setParameter("cityArea", cityArea).getResultList();
		String json = new Gson().toJson(list);
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public City getCityByCityName(String cityName) {
		City city = null;
		String hql = "FROM City WHERE cityName = :cityName";
		Session session = factory.getCurrentSession();
		List<City> list = session.createQuery(hql).setParameter("cityName", cityName).getResultList();
		if (list.size() != 0) {
			city = list.get(0);
		}
		return city;
	}

}
