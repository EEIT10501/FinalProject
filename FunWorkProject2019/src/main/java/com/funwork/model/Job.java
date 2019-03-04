package com.funwork.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Job {

	private Integer jobId;
	private String comment;
	private Boolean isExposure;
	private Boolean isFilled;
	private Timestamp postEndDate;
	private String reviewStatus;
	private String title;
	private Integer viewTimes;
	private City city;
	private String address;
	private String addresssup;
	private String contact;
	private String description;
	private String jobEmail;
	private String industry;
	private String other;
	private Date paidDate;
	private String jobPhone;
	private Integer positionNum;
	private Integer rateByHour;
	private User jobOwner;
	private Company jobCompany;

	public Job() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

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

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Column(columnDefinition = "nvarchar(255)")
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
	@JoinColumn(name = "Fk_City_Id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(columnDefinition = "nvarchar(255)")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(columnDefinition = "nvarchar(255)")
	public String getAddresssup() {
		return addresssup;
	}

	public void setAddresssup(String addresssup) {
		this.addresssup = addresssup;
	}

	@Column(columnDefinition = "nvarchar(255)")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(columnDefinition = "nvarchar(255)")
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

	@Column(columnDefinition = "nvarchar(255)")
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

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
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
	@JoinColumn(name = "Fk_JobOwner_Id")
	public User getJobOwner() {
		return jobOwner;
	}

	public void setJobOwner(User jobOwner) {
		this.jobOwner = jobOwner;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_Company_Id")
	public Company getJobCompany() {
		return jobCompany;
	}

	public void setJobCompany(Company jobCompany) {
		this.jobCompany = jobCompany;
	}

}
