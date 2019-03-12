package com.funwork.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.funwork.dao.SuggestionDao;
import com.funwork.model.Suggestion;

@Repository
public class SuggestionDaoImpl implements SuggestionDao {

	@Autowired
	SessionFactory factory;

	public SuggestionDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Suggestion> getAllSuggestions() {
		String hql = "FROM Suggestion";
		Session session = null;
		List<Suggestion> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

}