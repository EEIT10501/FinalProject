package com.funwork.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Salary {
	
	private Integer salaryId;
	private Float hours;
	private String reviewComment;
	private Integer reviewStatus;
	private Integer paymentstatus; //Jack老師建議新增付款狀態欄位
	private Float rating; //Jack老師建議新增評分欄位
	private Job Job;//外鍵
	private User user;//外鍵
	

	public Salary() {
	}
	
	
	
	public Salary(Integer salaryId, Float hours, String reviewComment, Integer reviewStatus, Integer paymentstatus,
			Float rating, com.funwork.model.Job job, User user) {
		super();
		this.salaryId = salaryId;
		this.hours = hours;
		this.reviewComment = reviewComment;
		this.reviewStatus = reviewStatus;
		this.paymentstatus = paymentstatus;
		this.rating = rating;
		this.Job = job;
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
		return paymentstatus;
	}

	public void setPaymentstatus(Integer paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}
<<<<<<< HEAD
	
	
=======

	@OneToOne(cascade = CascadeType.ALL)
>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git
	public Job getJob() {
		return Job;
	}

	public void setJob(Job job) {
		Job = job;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", hours=" + hours + ", reviewComment=" + reviewComment
				+ ", reviewStatus=" + reviewStatus + ", paymentstatus=" + paymentstatus + ", rating=" + rating
				+ ", JobId=" + Job.getJobId() + ", userId=" + user.getUserId() + "]";
	}

	
}

			  
			  