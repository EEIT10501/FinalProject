package com.funwork.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//雙向多對一中的一方

@Entity
@Table(name = "\"User\"")
public class User {

	private Integer userId;
	private String userName;
//	private String email;
	
	//以下為儲存多方的實例變數
	private Set<Company> companysSet = new HashSet<>();
	private Set<Job> jobsSet = new HashSet<>();
	private Set<Application> applicationsSet = new HashSet<>();
//	private Set<Salary> salarySet = new HashSet<>();
//	private Set<Attendence> attendenceSet = new HashSet<>();
	public User(Integer userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(nullable=false,columnDefinition="nvarchar(255)")
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//User 類別並沒有表示關聯的資訊 , 此資訊位於 Company 的 user 性質中
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	public Set<Company> getCompanysSet() {
		return companysSet;
	}

	public void setCompanysSet(Set<Company> companysSet) {
		this.companysSet = companysSet;
	}
	
	//User 類別並沒有表示關聯的資訊 , 此資訊位於 Job 的 jobOwner 性質中
	@OneToMany(mappedBy="jobOwner", cascade=CascadeType.ALL)
	public Set<Job> getJobsSet() {
		return jobsSet;
	}

	public void setJobsSet(Set<Job> jobsSet) {
		this.jobsSet = jobsSet;
	}

	//User 類別並沒有表示關聯的資訊 , 此資訊位於 Application 的 user 性質中
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	public Set<Application> getApplicationsSet() {
		return applicationsSet;
	}

	public void setApplicationsSet(Set<Application> applicationsSet) {
		this.applicationsSet = applicationsSet;
	}
//	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="Fk_Salary_Id", referencedColumnName="Salary_Id")
//	public Set<Salary> getSalarySet() {
//		return salarySet;
//	}
//
//	public void setSalarySet(Set<Salary> salarySet) {
//		this.salarySet = salarySet;
//	}
//	@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="fk_Attendence_Id", referencedColumnName="Attendence_Id")
//	public Set<Attendence> getAttendenceSet() {
//		return attendenceSet;
//	}
//
//	public void setAttendenceSet(Set<Attendence> attendenceSet) {
//		this.attendenceSet = attendenceSet;
//	}
	
	

}
