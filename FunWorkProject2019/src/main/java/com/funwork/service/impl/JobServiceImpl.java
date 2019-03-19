package com.funwork.service.impl;

import com.funwork.dao.CityDao;
import com.funwork.dao.CompanyDao;
import com.funwork.dao.JobDao;
import com.funwork.dao.NotificationDao;
import com.funwork.dao.UserDao;
import com.funwork.model.City;
import com.funwork.model.Job;
import com.funwork.model.Notification;
import com.funwork.service.JobService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Autowired
  UserDao userDao;

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

    Job job = jobDao.getJobById(jobId);
    job.setReviewStatus("發布中");
    job.setReviewTime(new Timestamp(System.currentTimeMillis()));
    jobDao.updateJob(job);

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

    Job job = jobDao.getJobById(jobId);
    job.setReviewStatus("審核失敗");
    job.setReviewTime(new Timestamp(System.currentTimeMillis()));
    job.setFailReason(failReason);
    jobDao.updateJob(job);

    Notification notification = new Notification();
    notification.setContent("您的職缺(" + job.getTitle() + ")審核失敗");
    notification.setTime(new Timestamp(System.currentTimeMillis()));
    notification.setType(2);
    notification.setUser(job.getJobOwner());
    notificationDao.insertNotification(notification);
    return job;
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
//    System.out.println(jbean.getAddress());
    
 // 地址轉經緯度
    JobServiceImpl jobServiceImpl = new JobServiceImpl();
    Map<String, String> latlng = jobServiceImpl.getGeocoderLatitude(jbean.getCityArea() + cityName + jbean.getAddress());
    // 設定經緯度
    jbean.setJobLat(latlng.get("lat"));
    jbean.setJobLng(latlng.get("lng"));
    jbean.setIsExposure(false);
    jbean.setIsFilled(false);
    jbean.setReviewStatus("待審核");
    jbean.setSubmitTime(new Timestamp(System.currentTimeMillis()));
    jbean.setViewTimes(0);
    jbean.setJobOwner(userDao.getUserById(userId));
    String companyName = jbean.getCompanyName();
    if (!companyName.equals("-1")) {
      jbean.setJobCompany(companyDao.findCompanyByUserAndName(userId, companyName));
    }
    jbean.setCity(cityDao.getCityByCityName(cityName));

    return jobDao.insertJob(jbean);
  }

  @Override
  public int getJobPostedCount(Integer userId) {
    return jobDao.getJobPostedCount(userId);
  }

  @Override
  public Map<String, String> getGeocoderLatitude(String address) {
    String apiKey = "AIzaSyBw-HiRWQLCjwq6fWJ-tFBcxECgNjWZZus";
    BufferedReader jsonres = null;

    try {
      URL url = new URL(
          "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + address + "&key=" + apiKey);

      jsonres = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
      String res;
      StringBuilder sb = new StringBuilder("");
      while ((res = jsonres.readLine()) != null) {
        sb.append(res.trim());
      }

      String str = sb.toString();
      System.out.println(str);

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
//  public static void main(String[] args) {  //測試用
//	  JobServiceImpl test = new JobServiceImpl();
//	  HashMap<String, String> map =  (HashMap<String, String>) test.getGeocoderLatitude("台北市大安區和平東路一段162號");
//	  System.out.println(map.get("lat"));
//	  System.out.println(map.get("lng"));
//  }
}
