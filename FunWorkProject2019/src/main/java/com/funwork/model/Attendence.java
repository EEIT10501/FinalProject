package com.funwork.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Attendence {

	private Integer attendenceId;
	private Date date;
	private Time time;
	private Integer type;
	private Float dailySalary;
	private Job job;
	private User user;

	public Attendence() {
	}

	public Attendence(Integer attendenceId, Date date, Time time, Integer type, Float dailySalary, Job job, User user) {
		this.attendenceId = attendenceId;
		this.date = date;
		this.time = time;
		this.type = type;
		this.dailySalary = dailySalary;
		this.job = job;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAttendenceId() {
		return attendenceId;
	}

	public void setAttendenceId(Integer attendenceId) {
		this.attendenceId = attendenceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getDailySalary() {
		return dailySalary;
	}

	public void setDailySalary(Float dailySalary) {
		this.dailySalary = dailySalary;
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
