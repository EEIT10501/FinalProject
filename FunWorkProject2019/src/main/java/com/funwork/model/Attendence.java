package com.funwork.model;

import java.sql.Date;
import java.sql.Timestamp;

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
	private Timestamp time;
	private Integer type;
	private Integer dailySalary;// Jack老師建議新增欄位 日薪欄位
	private Job job;  //通知Hibernate以此參考設定外鍵欄位
	private User user;  //通知Hibernate以此參考設定外鍵欄位

	public Attendence() {}
	
	public Attendence(Integer attendenceId, Date date, Timestamp time, Integer type, Integer dailySalary, Job job,
			User user) {
		super();
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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDailySalary() {
		return dailySalary;
	}

	public void setDailySalary(Integer dailySalary) {
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

	@Override
	public String toString() {
		return "Attendence [attendenceId=" + attendenceId + ", date=" + date + ", time=" + time + ", type=" + type
				+ ", dailySalary=" + dailySalary + ", jobId=" + job.getJobId() + ", userId=" + user.getUserId() + "]";
	}

}
