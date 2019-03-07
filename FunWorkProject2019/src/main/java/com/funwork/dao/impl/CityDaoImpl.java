package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.CityDao;
import com.funwork.model.City;

@Repository
public class CityDaoImpl implements CityDao {

	@Autowired
	SessionFactory factory;

	public CityDaoImpl() {
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
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

}
