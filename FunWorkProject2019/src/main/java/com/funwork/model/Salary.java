package com.funwork.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Salary ")
public class Salary {

	private Integer salaryId;
	private Float hours;
	private String reviewComment;
	private Integer reviewStatus;
	private Integer paymentStatus;
	private Float rating;
	private Job job;
	private User user;

	public Salary() {
	}

	public Salary(Integer salaryId, Float hours, String reviewComment, Integer reviewStatus, Integer paymentStatus,
			Float rating, Job job, User user) {
		this.salaryId = salaryId;
		this.hours = hours;
		this.reviewComment = reviewComment;
		this.reviewStatus = reviewStatus;
		this.paymentStatus = paymentStatus;
		this.rating = rating;
		this.job = job;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public Float getHours() {
		return hours;
	}

	public void setHours(Float hours) {
		this.hours = hours;
	}

	@Column(columnDefinition = "nvarchar(255)")
	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Integer getPaymentstatus() {
		return paymentStatus;
	}

	public void setPaymentstatus(Integer paymentstatus) {
		this.paymentStatus = paymentstatus;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_Job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_User_Id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
