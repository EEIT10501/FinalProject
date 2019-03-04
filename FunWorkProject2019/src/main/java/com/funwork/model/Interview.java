package com.funwork.model;

import java.sql.Clob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Interview")
public class Interview {
	private Integer interviewId;
	private Clob interviewComment;
	private String interviewType;
	private String interviewPlace;
	private Boolean interviewStatus;
	private Date interviewTime;

	// provide info to Hibernate for setting Fk
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
	public Clob getInterviewComment() {
		return interviewComment;
	}

	public void setInterviewComment(Clob interviewComment) {
		this.interviewComment = interviewComment;
	}
	
	@Column(nullable=false,columnDefinition="nvarchar(MAX) default '無'")
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

	@Column(nullable = false, columnDefinition = "int default 0")
	public Boolean getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(Boolean interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public Date getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}

	// Tell hibernate that application is for association
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
