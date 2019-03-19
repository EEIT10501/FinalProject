package com.funwork.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Schedule")
public class Schedule implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer scheduleId;
	private String scheduleName;
	private String color;
	private Timestamp endTime;
	private Timestamp startTime;
	private Float restHour;
	private Date workDate;
	private Job job;// fk
	private Float workingHours;
//	private User user;
	private Interview interview;

	public Schedule() {
	}

	public Schedule(Integer scheduleId, String scheduleName, String color, Timestamp endTime, Timestamp startTime, Float restHour,
			Date workDate, Job job, Float workingHours) {
		super();
		this.scheduleId = scheduleId;
		this.scheduleName = scheduleName;
		this.color = color;
		this.endTime = endTime;
		this.startTime = startTime;
		this.restHour = restHour;
		this.workDate = workDate;
		this.job = job;
		this.workingHours = workingHours;
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

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "Fk_User_Id")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	@Transient
	public Float getWorkingHours() throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm");
		String endTime = simpleFormat.format(getEndTime());
		String startTime = simpleFormat.format(getStartTime());
		long end = simpleFormat.parse(endTime).getTime();
		long start = simpleFormat.parse(startTime).getTime();
		long diff = (end - start);
		float days = diff / (1000 * 60 * 60 * 24);
		float hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		float dayhours = hours - getRestHour();
		return dayhours;
	}

	@Transient
	public void setWorkingHours(Float workingHours) throws ParseException {
		this.workingHours = workingHours;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_Interview_Id")
	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

}
