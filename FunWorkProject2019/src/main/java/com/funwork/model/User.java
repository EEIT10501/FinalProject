package com.funwork.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//雙向多對一中的一方

@Entity
@Table(name = "\"User\"")
public class User {

	private Integer userId;
	private String userName;
	private String password;
	private Integer phoneNum;
	private String email;
	private Integer mebershipLevel;
	private Integer exposureLimit;
	private Integer jobPostLimit;
	private Integer jobPostPeriod;
	private Integer rating;
	private Integer role;
	private Integer abscence;
	private String facebook;
	private String google;

	// 以下為儲存多方的實例變數
	Set<Company> companysSet = new HashSet<>();
	Set<Job> jobsSet = new HashSet<>();
	Set<Application> applicationsSet = new HashSet<>();
	Set<Salary> salarySet = new HashSet<>();
	Set<Attendence> attendenceSet = new HashSet<>();

	public User() {
	}
	public User(Integer userId, String userName) {

		this.userId = userId;
		this.userName = userName;
	}

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

	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
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

	// User 類別並沒有表示關聯的資訊 , 此資訊位於 Company 的 user 性質中
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Company> getCompanysSet() {
		return companysSet;
	}

	public void setCompanysSet(Set<Company> companysSet) {
		this.companysSet = companysSet;
	}

	// User 類別並沒有表示關聯的資訊 , 此資訊位於 Job 的 jobOwner 性質中
	@OneToMany(mappedBy = "jobOwner", cascade = CascadeType.ALL)
	public Set<Job> getJobsSet() {
		return jobsSet;
	}

	public void setJobsSet(Set<Job> jobsSet) {
		this.jobsSet = jobsSet;
	}

	// User 類別並沒有表示關聯的資訊 , 此資訊位於 Application 的 user 性質中
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public Set<Application> getApplicationsSet() {
		return applicationsSet;
	}

	public void setApplicationsSet(Set<Application> applicationsSet) {
		this.applicationsSet = applicationsSet;
	}

}
