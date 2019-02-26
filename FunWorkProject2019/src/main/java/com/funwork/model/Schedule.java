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
import javax.persistence.OneToOne;

@Entity
public class Schedule {
	
	private Integer scheduleId;
	private Timestamp endTime;
	private Timestamp startTime;
	private Date workDate;
	private Date workDate1;

	public Schedule() {
	}

	public Schedule(Integer scheduleId, Timestamp endTime, Timestamp startTime, Date workDate) {
		super();
		this.scheduleId = scheduleId;
		this.endTime = endTime;
		this.startTime = startTime;
		this.workDate = workDate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getScheduleId() {
		return scheduleId;
	}


	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}


	public Timestamp getEndTime() {
		return endTime;
	}


	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}


	public Timestamp getStartTime() {
		return startTime;
	}


	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Date getWorkDate() {
		return workDate;
	}


	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

<<<<<<< HEAD
=======
	@OneToOne(cascade = CascadeType.ALL)
	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git
	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", endTime=" + endTime + ", startTime=" + startTime
				+ ", workDate=" + workDate + "]";
	}

	
}
