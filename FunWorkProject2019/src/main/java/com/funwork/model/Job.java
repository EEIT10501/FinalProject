package com.funwork.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Job {
	
	private Integer jobId;
//	private Integer applicant; //好像跟jobOwner重複
	private String comment;
	private Boolean isExposure; //這是什麼??
	private Boolean isFilled; //這是什麼??
	private Timestamp postEndDate;
	private Integer reviewStatus;
	private String title;
	private Integer viewTimes;
	private City city; //多做一張城市的Table
	private String address;
	private String addresssup;
	private String contact;
	private String description;
	private String jobEmail;
	private String industry;
	private String other;
	private Timestamp paidDate;
	private String jobPhone;
	private Integer positionNum;
	private Integer rateByHour; 
	private User jobOwner;//通知Hibernate以此參考設定外鍵欄位
	private Company jobCompany;//通知Hibernate以此參考設定外鍵欄位
	
	public Job() {
	}

	//以下為儲存多方的實例變數
	Set<Application> applcationsSet = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

//	public Integer getApplicant() {
//		return applicant;
//	}
//
//	public void setApplicant(Integer applicant) {
//		this.applicant = applicant;
//	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

	public Boolean getIsExposure() {
		return isExposure;
	}

	public void setIsExposure(Boolean isExposure) {
		this.isExposure = isExposure;
	}

	public Boolean getIsFilled() {
		return isFilled;
	}

	public void setIsFilled(Boolean isFilled) {
		this.isFilled = isFilled;
	}

	public Timestamp getPostEndDate() {
		return postEndDate;
	}

	public void setPostEndDate(Timestamp postEndDate) {
		this.postEndDate = postEndDate;
	}

	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_jobCity_Id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddresssup() {
		return addresssup;
	}

	public void setAddresssup(String addresssup) {
		this.addresssup = addresssup;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobEmail() {
		return jobEmail;
	}

	public void setJobEmail(String jobEmail) {
		this.jobEmail = jobEmail;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Timestamp getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Timestamp paidDate) {
		this.paidDate = paidDate;
	}

	public String getJobPhone() {
		return jobPhone;
	}

	public void setJobPhone(String jobPhone) {
		this.jobPhone = jobPhone;
	}

	public Integer getPositionNum() {
		return positionNum;
	}

	public void setPositionNum(Integer positionNum) {
		this.positionNum = positionNum;
	}

	public Integer getRateByHour() {
		return rateByHour;
	}

	public void setRateByHour(Integer rateByHour) {
		this.rateByHour = rateByHour;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_jobOwner_Id")
	public User getJobOwner() {
		return jobOwner;
	}

	public void setJobOwner(User jobOwner) {
		this.jobOwner = jobOwner;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_jobCompany_Id")
	public Company getJobCompany() {
		return jobCompany;
	}

	public void setJobCompany(Company jobCompany) {
		this.jobCompany = jobCompany;
	}
	
	//Job 類別並沒有表示關聯的資訊 , 此資訊位於 Application 的 job 性質中
	@OneToMany(mappedBy="job",cascade=CascadeType.ALL)
	public Set<Application> getApplcationsSet() {
		return applcationsSet;
	}

	public void setApplcationsSet(Set<Application> applcationsSet) {
		this.applcationsSet = applcationsSet;
	}
	
}
	

