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
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class JobServiceImpl implements JobService {
  public static final Logger logger = Logger.getLogger("com.funwork");
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
  public List<Job> getJobReviewList() {
    return jobDao.getJobReviewList();
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
  public List<City> getCityName(Integer cityId) {
    return cityDao.getCityName(cityId);

  }

  @Override
  public List<Job> findJobByUserId(Integer userId) {
    return jobDao.findJobByUserId(userId);
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
  public String getCityNameList(String cityArea) {
    return cityDao.getCityNameList(cityArea);
  }

  @Override
  public void insertJob(Job jbean, Integer userId) {
    String cityName = jbean.getCityName();
    jbean.setAddress(jbean.getCityArea() + cityName + jbean.getAddress());

    // 地址轉經緯度
    JobServiceImpl jobServiceImpl = new JobServiceImpl();
    Map<String, String> latlng = jobServiceImpl
        .getGeocoderLatitude(jbean.getCityArea() + cityName + jbean.getAddress());
    // 設定經緯度
    jbean.setJobLat(latlng.get("lat"));
    jbean.setJobLng(latlng.get("lng"));
    if (jbean.getOther() == null || jbean.getOther().trim().length() == 0) {
      jbean.setOther("給雇主的話");
    }
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

    jobDao.insertJob(jbean);
  }

  @Override
  public int getJobPostedCount(Integer userId) {
    return jobDao.getJobPostedCount(userId);
  }

  @Override
  public Map<String, String> getGeocoderLatitude(String address) {
    String apiKey = "AIzaSyBw-HiRWQLCjwq6fWJ-tFBcxECgNjWZZus";
    URL url = null;
    try {
      url = new URL(
          "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + address + "&key=" + apiKey);
    } catch (MalformedURLException e) {
      logger.warning(e.getMessage());
    }
    if (url == null) {
      return null;
    }
    try (BufferedReader jsonres = new BufferedReader(
        new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));) {

      String res;
      StringBuilder sb = new StringBuilder("");
      while ((res = jsonres.readLine()) != null) {
        sb.append(res.trim());
      }

      String str = sb.toString();

      if (StringUtils.isNotEmpty(str)) {
        JSONObject json = new JSONObject(str);
        JSONArray ja = json.getJSONArray("results");
        String lat = String.valueOf(ja.getJSONObject(0).getJSONObject("geometry")
                .getJSONObject("location").getDouble("lat"));
        String lng = String.valueOf(ja.getJSONObject(0).getJSONObject("geometry")
                .getJSONObject("location").getDouble("lng"));
        Map<String, String> map = new HashMap<String, String>();
        if (lat != null && lng != null) {
          map.put("lat", lat);
          map.put("lng", lng);
          return map;
        }
      }
    } catch (Exception e) {
      logger.warning(e.getMessage());
    }
    return null; 
  }

  @Override
  public List<Job> getJobsBySearchStr(String searchStr) {
    return jobDao.getJobsBySearchStr(searchStr);
  }

  @Override
  public void changeJobExposure(Integer jobId) {
    Job job = jobDao.getJobById(jobId);
    if (job.getIsExposure()) {
      job.setIsExposure(false);
      jobDao.updateJob(job);
    } else {
      job.setIsExposure(true);
      jobDao.updateJob(job);
    }
  }

  @Override
  public Integer getJobExposureCount(Integer userId) {
    return jobDao.getJobExposureCount(userId);
  }

  @Override
  public void changeJobFilled(Integer jobId) {
    Job job = jobDao.getJobById(jobId);
    if (job.getIsFilled()) {
      job.setIsFilled(false);
      jobDao.updateJob(job);
    } else {
      job.setIsFilled(true);
      job.setIsExposure(false);
      jobDao.updateJob(job);
    }
  }

  @Override
  public void updateJobPostById(Integer jobId, Job jbean) {
    Job jobToChange = jobDao.getJobById(jobId);
    jobToChange.setIndustry(jbean.getIndustry());
    jobToChange.setTitle(jbean.getTitle());
    jobToChange.setDescription(jbean.getDescription());
    jobToChange.setPositionNum(jbean.getPositionNum());
    jobToChange.setOther(jbean.getOther());
    jobToChange.setPostEndDate(jbean.getPostEndDate());
    jobToChange.setWorkDate(jbean.getWorkDate());
    jobToChange.setWorkTime(jbean.getWorkTime());
    String cityName = jbean.getCityName();
    String addr = jbean.getCityArea() + cityName + jbean.getAddress();
    jobToChange.setAddress(addr);
    jobToChange.setAddresssup(jbean.getAddresssup());
    jobToChange.setPaidDate(jbean.getPaidDate());
    jobToChange.setRateByHour(jbean.getRateByHour());
    jobToChange.setJobPhone(jbean.getJobPhone());
    jobToChange.setJobEmail(jbean.getJobEmail());
    jobToChange.setContact(jbean.getContact());
    jobToChange.setCompanyName(jbean.getCompanyName());
    jobToChange.setComment(jbean.getComment());
    jobDao.updateJob(jobToChange);
  }

  @Override
  public Integer updateViewTimesByJob(Integer jobId) {
    Job job = jobDao.getJobById(jobId);
    Integer currentViewTime = job.getViewTimes();
    if (currentViewTime == 0) {
      job.setViewTimes(1);
    } else {
      currentViewTime++;
    }
    return currentViewTime;
  }

  @Override
  public City getCityByPk(Integer cityId) {
    return cityDao.getCityByPk(cityId);
  }

  @Override
  public Integer getAllJobPostingCount() {
    return jobDao.getAllJobPostingCount();
  }

  @Override
  public String getAllPostingJobTypeJson() {
    return jobDao.getAllPostingJobTypeJson();
  }

  @Override
  public void updateJobByExpired() {
    jobDao.updateJobByExpired();
  }

}
