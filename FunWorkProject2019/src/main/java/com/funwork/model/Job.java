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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Job")
public class Job {
	
	private Integer jobId;
	private String title;
	
	//以下為儲存多方的實例變數
	Set<Application> applcationsSet = new HashSet<>();
	
	//通知Hibernate以此參考設定外鍵欄位
	private User user;
	private Company company;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	@Column(nullable=false,columnDefinition="nvarchar(255)")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	//Job 類別並沒有表示關聯的資訊 , 此資訊位於 Application 的 job 性質中
	@OneToMany(mappedBy="job",cascade=CascadeType.ALL)
	public Set<Application> getApplcationsSet() {
		return applcationsSet;
	}

	public void setApplcationsSet(Set<Application> applcationsSet) {
		this.applcationsSet = applcationsSet;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_companyId")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}
