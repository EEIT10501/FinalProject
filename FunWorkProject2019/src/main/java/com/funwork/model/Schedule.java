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
	private String scheduleName;
	private String color;
	private Time endTime;
	private Time startTime;
	private Float restHour;
	private Date workDate;
	
	private Job job;

	public Schedule() {
	}

	public Schedule(Integer scheduleId, String scheduleName, String color, Time endTime, Time startTime, Float restHour,
			Date workDate, Job job) {
		super();
		this.scheduleId = scheduleId;
		this.scheduleName = scheduleName;
		this.color = color;
		this.endTime = endTime;
		this.startTime = startTime;
		this.restHour = restHour;
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
	@JoinColumn(name = "Fk_Job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Float getRestHour() {
		return restHour;
	}

	public void setRestHour(Float restHour) {
		this.restHour = restHour;
	}


	
	

}
