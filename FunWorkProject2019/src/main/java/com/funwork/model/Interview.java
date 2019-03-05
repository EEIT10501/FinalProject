package com.funwork.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Interview {
	private Integer interviewId;
	private String interviewComment;
	private String interviewType;
	private String interviewPlace;
	private String interviewStatus;
	private Timestamp interviewTime;
	private Application application;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	@Column(columnDefinition = "nvarchar(MAX)")
	public String getInterviewComment() {
		return interviewComment;
	}

	public void setInterviewComment(String interviewComment) {
		this.interviewComment = interviewComment;
	}

	@Column(nullable = false, columnDefinition = "nvarchar(MAX) default '無'")
	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	public String getInterviewPlace() {
		return interviewPlace;
	}

	public void setInterviewPlace(String interviewPlace) {
		this.interviewPlace = interviewPlace;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public Timestamp getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Timestamp interviewTime) {
		this.interviewTime = interviewTime;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_applicationId")
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewComment=" + interviewComment + ", interviewType="
				+ interviewType + ", interviewPlace=" + interviewPlace + ", interviewStatus=" + interviewStatus
				+ ", interviewTime=" + interviewTime + ", applicationId=" + application.getApplicationId() + "]";
	}

}
