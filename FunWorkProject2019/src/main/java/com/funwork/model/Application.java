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

/**
 * @author Yang Cheng User 和 Application (雙向多對一) 做關聯性 One user corresponds to
 *         multiple applications while one application corresponds to one user.
 * 
 *         Job 和 Application (雙向多對一) 做關聯性 One job corresponds to multiple
 *         applications while one application corresponds to one job.
 */
@Entity
@Table(name = "Application")
public class Application {
	private Integer applicationId;
	private String answer;
	private java.util.Date applicationTime;
	private String appliedStatus;

	// 以下為儲存多方的實例變數
	Set<Interview> interviewsSet = new HashSet<>();

	// 以下兩行讓Hibernate設定外鍵欄位
	private User user;
	private Job job;

	public Application() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(nullable = false)
	public java.util.Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(java.util.Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	@Column(nullable = false)
	public String getAppliedStatus() {
		return appliedStatus;
	}

	public void setAppliedStatus(String appliedStatus) {
		this.appliedStatus = appliedStatus;
	}

	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
	public Set<Interview> getInterviewsSet() {
		return interviewsSet;
	}

	public void setInterviewsSet(Set<Interview> interviewsSet) {
		this.interviewsSet = interviewsSet;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_jobId")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", answer=" + answer + ", applicationTime="
				+ applicationTime + ", appliedStatus=" + appliedStatus + ", userId=" + user.getUserId() + ", jobId="
				+ job.getJobId() + "]";
	}

}
