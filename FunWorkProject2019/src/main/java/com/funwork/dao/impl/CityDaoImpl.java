package com.funwork.dao.impl;

import com.funwork.dao.CityDao;
import com.funwork.model.City;
import com.google.gson.Gson;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl implements CityDao {

  @Autowired
  SessionFactory factory;

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
    session = factory.getCurrentSession();
    return session.createQuery(hql).getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getCityNameList(String cityArea) {
    String hql = "SELECT DISTINCT cityName FROM City WHERE cityArea = :cityArea";
    Session session = factory.getCurrentSession();
    List<String> list = session.createQuery(hql).setParameter("cityArea", cityArea).getResultList();
    return new Gson().toJson(list);
  }

  @SuppressWarnings("unchecked")
  @Override
  public City getCityByCityName(String cityName) {
    City city = null;
    String hql = "FROM City WHERE cityName = :cityName";
    Session session = factory.getCurrentSession();
    List<City> list = session.createQuery(hql).setParameter("cityName", cityName).getResultList();
    if (!list.isEmpty()) {
      city = list.get(0);
    }
    return city;
  }

  @Override
  public City getCityByPk(Integer cityId) {
    String hql = "FROM City WHERE cityId = :cityId";
    Session session = factory.getCurrentSession();
    return (City) session.createQuery(hql).setParameter("cityId", cityId).getSingleResult();
  }

}
