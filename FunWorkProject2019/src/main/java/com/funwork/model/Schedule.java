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
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class Schedule {

	private Integer scheduleId;
	private Time endTime;
	private Time startTime;
	private Date workDate;
	private Job job; // 通知Hibernate以此參考設定外鍵欄位

	public Schedule() {
	}

	public Schedule(Integer scheduleId, Time endTime, Time startTime, Date workDate, Job job) {
		this.scheduleId = scheduleId;
		this.endTime = endTime;
		this.startTime = startTime;
		this.workDate = workDate;
		this.job = job;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_Job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}
