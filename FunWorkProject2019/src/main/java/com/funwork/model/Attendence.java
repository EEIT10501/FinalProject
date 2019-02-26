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
import javax.persistence.OneToOne;

@Entity
public class Attendence {

	private Integer attendenceId;
	private Date date;
	private Timestamp time;
	private Integer type;
	private Integer dailySalary;// Jack老師建議新增欄位 日薪欄位
	private Job job;// 外鍵
	private User user;// 外鍵

	public Attendence() {
	}
<<<<<<< HEAD
	
	
	
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


=======
>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git

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
<<<<<<< HEAD
	
	@ManyToOne
	@JoinColumn(name="fk_Job_id",nullable=false)
=======

	@OneToOne(cascade = CascadeType.ALL)
>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
<<<<<<< HEAD
	@ManyToOne
	@JoinColumn(name="fk_user_id",nullable=false)
=======

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Fk_User_Id")
>>>>>>> branch 'master' of https://github.com/EEIT10501/FinalProject.git
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
