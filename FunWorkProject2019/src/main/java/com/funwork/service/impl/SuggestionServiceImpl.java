package com.funwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.SuggestionDao;
import com.funwork.model.Suggestion;
import com.funwork.service.SuggestionService;


@Service
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionDao dao;

	public SuggestionServiceImpl() {
	}

	
	@Transactional
	@Override
	public List<Suggestion> getAllSuggestions() {
		return dao.getAllSuggestions();
	}

}


