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
public class Complaint {

	private Integer complaintId;
	private String content;
	private Timestamp process_time;
	private Timestamp submitted_time;
	private Integer type;
	private Integer status;

	private Job job;
	private User user;

	public Complaint() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getComplaint() {
		return complaintId;
	}

	public void setComplaint(Integer complaintId) {
		this.complaintId = complaintId;
	}

	@Column(columnDefinition = "nvarchar(255)")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getProcess_time() {
		return process_time;
	}

	public void setProcess_time(Timestamp process_time) {
		this.process_time = process_time;
	}

	public Timestamp getSubmitted_time() {
		return submitted_time;
	}

	public void setSubmitted_time(Timestamp submitted_time) {
		this.submitted_time = submitted_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_user_Id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
