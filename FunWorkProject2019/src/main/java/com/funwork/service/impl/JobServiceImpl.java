package com.funwork.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funwork.dao.CityDao;
import com.funwork.dao.CompanyDao;
import com.funwork.dao.JobDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.service.JobService;
import com.google.api.client.json.Json;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		//地址轉經緯度
		JobServiceImpl jobServiceImpl = new JobServiceImpl();
		Map<String,String> latlng = jobServiceImpl.getGeocoderLatitude(jbean.getAddress());

		String cityName = jbean.getCityName();
		jbean.setAddress(jbean.getCityArea() + cityName + jbean.getAddress());
		//設定經緯度
		jbean.setJobLat(latlng.get("lat"));
		jbean.setJobLng(latlng.get("lng"));
		
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

	@Override
	public Map<String, String> getGeocoderLatitude(String address) {
		String apiKey = "AIzaSyBw-HiRWQLCjwq6fWJ-tFBcxECgNjWZZus";
		BufferedReader jsonres = null;

		try {
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + address
					+ "&key=" + apiKey);

			jsonres = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = jsonres.readLine()) != null) {
				sb.append(res.trim());
			}

			String str = sb.toString();		
//			System.out.println(str);
			
			Map<String, String> map = null;
			if (StringUtils.isNotEmpty(str)) {
				int latStart = str.indexOf("{\"location\" : {\"lat\" :");
				int latEnd = str.indexOf(",\"lng");
				int lngEnd = str.indexOf("},\"location_type\"");
				if (latStart > 0 && lngEnd > 0 && latEnd > 0) {
					String lat = str.substring(latStart + 23, latEnd);
					String lng = str.substring(latEnd + 9, lngEnd);
					map = new HashMap<String, String>();
					map.put("lng", lng);
					map.put("lat", lat);
					return map;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jsonres.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

//	public static void main(String[] args) {
//		JobServiceImpl jobServiceImpl = new JobServiceImpl();
//		Map<String, String> json = jobServiceImpl.getGeocoderLatitude("台北市大安區信義路三段168號");
//		System.out.println("lng : " + json.get("lng"));
//		System.out.println("lat : " + json.get("lat"));
//	}

}
