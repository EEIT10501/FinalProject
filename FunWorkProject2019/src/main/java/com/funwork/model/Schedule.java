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
	private Job job;//外鍵
	

	public Schedule() {
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

	@OneToOne(cascade = CascadeType.ALL)
	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", endTime=" + endTime + ", startTime=" + startTime
				+ ", workDate=" + workDate + ", jobId=" + job.getJobId() + "]";
	}
	
}
