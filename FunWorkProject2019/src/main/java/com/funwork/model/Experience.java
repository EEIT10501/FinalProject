package com.funwork.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Experience\"")
public class Experience {
	
	private Integer experienceId;
	private String company;
	private String position;
	private Timestamp term;
	private Reusme reusme;//外鍵

	public Experience() {
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
	}


	@Column(columnDefinition="nvarchar(255)")
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public Timestamp getTerm() {
		return term;
	}


	public void setTerm(Timestamp term) {
		this.term = term;
	}

	@Column(columnDefinition="nvarchar(255)")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name="FK_Resume_Id")
	public Reusme getReusme() {
		return reusme;
	}


	public void setReusme(Reusme reusme) {
		this.reusme = reusme;
	}







	
	
	
}
				  