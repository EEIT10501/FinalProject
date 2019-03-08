package com.funwork.model;

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
public class Application {
	private Integer applicationId;
	private String answer;
	private Timestamp applicationTime;
	private String appliedStatus;
	private User user;
	private Job job;

	public Application() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	@Column(nullable = false,columnDefinition = "nvarchar(255)")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Timestamp applicationTime) {
		this.applicationTime = applicationTime;
	}

//	@Column(nullable = false)
	public String getAppliedStatus() {
		return appliedStatus;
	}

	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_User_Id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_Job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", answer=" + answer + ", applicationTime="
				+ applicationTime + ", appliedStatus=" + appliedStatus + ", userId=" + user.getUserId() + ", jobId="
				+ job.getJobId() + "]";
	}

}
