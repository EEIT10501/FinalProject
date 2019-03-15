package com.funwork.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.CityDao;
import com.funwork.dao.CompanyDao;
import com.funwork.dao.JobDao;
import com.funwork.dao.NotificationDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.service.JobService;

@Transactional
@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobDao jobDao;
	@Autowired
	CityDao cityDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	NotificationDao notificationDao;

	public JobServiceImpl() {
	}

	@Override
	public List<Job> getAllJobs() {
		return jobDao.getAllJobs();
	}

	@Override
	public List<Job> getJobReviewList() {
		return jobDao.getJobReviewList();
	}

	@Override
	public List<Job> getJobPassed() {
		return jobDao.getJobPassed();
	}

	@Override
	public List<Job> getJobByCityName(Integer cityId) {
		return jobDao.getJobByCityName(cityId);
	}

	@Override
	public List<Job> getJobByCityArea(Integer cityId) {
		return jobDao.getJobByCityArea(cityId);
	}

	@Override
	public Job getJobById(Integer jobId) {
		return jobDao.getJobById(jobId);
	}

	@Override
	public Job jobReviewPass(Integer jobId) {
		Job job = jobDao.jobReviewPass(jobId);
		Notification notification = new Notification();
		notification.setContent("您的職缺(" + job.getTitle() + ")已通過審核");
		notification.setTime(new Timestamp(System.currentTimeMillis()));
		notification.setType(2);
		notification.setUser(job.getJobOwner());
		notificationDao.insertNotification(notification);
		return job;
	}

	@Override
	public Job jobReviewFail(Integer jobId, String failReason) {
		Job job = jobDao.jobReviewFail(jobId, failReason);
		Notification notification = new Notification();
		notification.setContent("您的職缺(" + job.getTitle() + ")審核失敗");
		notification.setTime(new Timestamp(System.currentTimeMillis()));
		notification.setType(2);
		notification.setUser(job.getJobOwner());
		notificationDao.insertNotification(notification);
		return job;
	}

	@Override
	public Job jobRemove(Integer jobId, String removeReason) {
		return jobDao.jobRemove(jobId, removeReason);
	}

	@Override
	public List<City> getAllCitys() {
		return cityDao.getAllCitys();

	}

	@Override
	public List<City> getCityName(Integer cityId) {
		return cityDao.getCityName(cityId);

	}

	@Override
	public List<Job> findJobByUserId(Integer userId) {
		return jobDao.findJobByUserId(userId);
	}

	@Override
	public List<Job> findJobByUserIdNJobStatus(Integer userId) {
		return jobDao.findJobByUserIdNJobStatus(userId);
	}

	@Override
	public List<Job> getCorrectJobs() {
		return jobDao.getCorrectJobs();
	}

	@Override
	public List<Job> getReviewHistory() {
		return jobDao.getReviewHistory();
	}

	@Override
	public List<String> getCityAreaList() {
		return cityDao.getCityAreaList();
	}

	@Override
	public String getCityNameList(String cityArea) {
		return cityDao.getCityNameList(cityArea);
	}

	@Override
	public City getCityByCityName(String cityName) {
		return cityDao.getCityByCityName(cityName);
	}

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
		jbean.setCity(cityDao.getCityByCityName(cityName));

		Job job = jobDao.insertJob(jbean, userId);
		return job;
	}

	@Override
	public int getJobPostedCount(Integer userId) {
		return jobDao.getJobPostedCount(userId);
	}

}
