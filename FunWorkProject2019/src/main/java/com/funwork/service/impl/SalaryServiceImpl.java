package com.funwork.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.SalaryDao;
import com.funwork.model.Salary;
import com.funwork.service.SalaryService;



@Service
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryDao dao;

	public SalaryServiceImpl() {
	}

	@Transactional
	@Override
	public List<Salary> getAllSalarys() {
		return dao.getAllSalarys();
	}

}
