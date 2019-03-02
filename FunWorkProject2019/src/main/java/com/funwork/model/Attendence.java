package com.funwork.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Attendence")
public class Attendence {

	private Integer attendenceId;
	private Date date;
	private Time time;
	private Integer type;
	private Float dailySalary;// Jack老師建議新增欄位 日薪欄位
	private Job job;  //通知Hibernate以此參考設定外鍵欄位
	private User user;  //通知Hibernate以此參考設定外鍵欄位

	public Attendence() {}
	
	public Attendence(Integer attendenceId, Date date, Time time, Integer type, Float dailySalary, Job job,
			User user) {
		super();
		this.attendenceId = attendenceId;
		this.date = date;
		this.time = time;
		this.type = type;
		this.dailySalary = dailySalary;
//		this.job = job;
//		this.user = user;
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
	@JoinColumn(name = "fk_Job_Id")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_User_Id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
