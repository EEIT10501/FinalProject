package com.funwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"Experience\"")
public class Experience {
	
	private Integer id;
	private String experienceId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="experience_id")
	public String getExperienceId() {
		return experienceId;
	}


	public void setExperienceId(String experienceId) {
		this.experienceId = experienceId;
	}

	private String industry;
	private String position;
	//private Reusme reusme;//外鍵
	private Integer resumeId;

	public Experience() {
	}

	
	@Column(name="FK_Resume_Id")
	public Integer getResumeId() {
		return resumeId;
	}

	
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	
	
}
				  