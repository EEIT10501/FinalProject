package com.funwork.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.CityDao;
import com.funwork.dao.CompanyDao;
import com.funwork.dao.JobDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobDao dao;

	@Autowired
	CityDao citydao;
	@Autowired
	CompanyDao companyDao;

	public JobServiceImpl() {
	}

	@Override
	@Transactional
	public List<Job> getAllJobs() {
		return dao.getAllJobs();
	}

	@Override
	@Transactional
	public List<Job> getJobReviewList() {
		return dao.getJobReviewList();
	}

	@Override
	@Transactional
	public List<Job> getJobPassed() {
		return dao.getJobPassed();
	}

	@Override
	@Transactional
	public List<Job> getJobByCityName(Integer cityId) {
		return dao.getJobByCityName(cityId);
	}

	@Transactional
	@Override
	public List<Job> getJobByCityArea(Integer cityId) {
		return dao.getJobByCityArea(cityId);
	}

	@Override
	@Transactional
	public Job getJobById(Integer jobId) {
		return dao.getJobById(jobId);
	}

	@Override
	@Transactional
	public Job jobReviewPass(Integer jobId) {
		return dao.jobReviewPass(jobId);
	}

	@Override
	@Transactional
	public Job jobReviewFail(Integer jobId, String failReason) {
		return dao.jobReviewFail(jobId, failReason);
	}

	@Transactional
	@Override
	public Job jobRemove(Integer jobId, String removeReason) {
		return dao.jobRemove(jobId, removeReason);
	}

	@Transactional
	@Override
	public List<City> getAllCitys() {
		return citydao.getAllCitys();

	}

	@Transactional
	@Override
	public List<City> getCityName(Integer cityId) {
		return citydao.getCityName(cityId);

	}

	@Transactional
	@Override
	public List<Job> findJobByUserId(Integer userId) {
		return dao.findJobByUserId(userId);
	}

	@Transactional
	@Override
	public List<Job> findJobByUserIdNJobStatus(Integer userId) {
		return dao.findJobByUserIdNJobStatus(userId);
	}

	@Transactional
	@Override
	public List<Job> getCorrectJobs() {
		return dao.getCorrectJobs();
	}

	@Transactional
	@Override
	public List<Job> getReviewHistory() {
		return dao.getReviewHistory();
	}

	@Transactional
	@Override
	public List<String> getCityAreaList() {
		return citydao.getCityAreaList();
	}

	@Transactional
	@Override
	public String getCityNameList(String cityArea) {
		return citydao.getCityNameList(cityArea);
	}

	@Transactional
	@Override
	public City getCityByCityName(String cityName) {
		return citydao.getCityByCityName(cityName);
	}

	@Transactional
	@Override
	public Job insertJob(Job jbean, Integer userId) {

		String cityName = jbean.getCityName();
		jbean.setAddress(jbean.getCityArea() + cityName + jbean.getAddress());
		jbean.setIsExposure(false);
		jbean.setIsFilled(false);
		jbean.setReviewStatus("待審核");
		jbean.setSubmitTime(new Timestamp(System.currentTimeMillis()));
		jbean.setViewTimes(0);
		String companyName = jbean.getCompanyName();
		if (!companyName.equals("-1")) {
			jbean.setJobCompany(companyDao.findCompanyByUserAndName(userId, companyName));
		}
		jbean.setCity(citydao.getCityByCityName(cityName));

		Job job = dao.insertJob(jbean, userId);
		return job;
	}

	@Transactional
	@Override
	public int getJobPostedCount(Integer userId) {
		return dao.getJobPostedCount(userId);
	}

}
