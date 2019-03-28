package com.funwork.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer userId;
  private String userName;
  private String password;
  private String phoneNum;
  private String email;
  private Integer mebershipLevel;
  private Integer exposureLimit;
  private Integer jobPostLimit;
  private Integer jobPostPeriod;
  private Double rating;
  private Integer role;
  private Integer abscence;
  private Integer presence;
  private String facebook;
  private String google;

  public Date getVipEndDate() {
    return vipEndDate;
  }

  public void setIsOpen(Boolean isOpen) {
    this.isOpen = isOpen;
  }

  private Boolean isOpen;
  private Date vipEndDate;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  @Column(nullable = false, columnDefinition = "nvarchar(255)")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  @Column(nullable = false, unique = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getMebershipLevel() {
    return mebershipLevel;
  }

  public void setMebershipLevel(Integer mebershipLevel) {
    this.mebershipLevel = mebershipLevel;
  }

  public Integer getExposureLimit() {
    return exposureLimit;
  }

  public void setExposureLimit(Integer exposureLimit) {
    this.exposureLimit = exposureLimit;
  }

  public Integer getJobPostLimit() {
    return jobPostLimit;
  }

  public void setJobPostLimit(Integer jobPostLimit) {
    this.jobPostLimit = jobPostLimit;
  }

  public Integer getJobPostPeriod() {
    return jobPostPeriod;
  }

  public void setJobPostPeriod(Integer jobPostPeriod) {
    this.jobPostPeriod = jobPostPeriod;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public Integer getAbscence() {
    return abscence;
  }

  public void setAbscence(Integer abscence) {
    this.abscence = abscence;
  }

  public String getFacebook() {
    return facebook;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public String getGoogle() {
    return google;
  }

  public void setGoogle(String google) {
    this.google = google;
  }

  public Boolean getIsOpen() {
    return isOpen;
  }

  public void setVipEndDate(Date vipEndDate) {
    this.vipEndDate = vipEndDate;
  }

  public Integer getPresence() {
    return presence;
  }

  public void setPresence(Integer presence) {
    this.presence = presence;
  }

}