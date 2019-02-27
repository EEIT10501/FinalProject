package com.funwork.model;

import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"Reusme\"")
public class Reusme {

	private Integer resumeId;
	private String name;
	private Integer phoneNum;
	private Timestamp bitrh;
	private String educationLevel;
	private String profilePic;
	private String selfIntro;
	private User user;//外鍵

	
	//以下為儲存多方的實例變數
	Set<Experience> experiencesSet = new HashSet<>();
	
	
	public Reusme() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getResumeId() {
		return resumeId;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
	@Column(columnDefinition="nvarchar(255)")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Timestamp getBitrh() {
		return bitrh;
	}

	public void setBitrh(Timestamp bitrh) {
		this.bitrh = bitrh;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getSelfIntro() {
		return selfIntro;
	}

	public void setSelfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Fk_User_Id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany
	@JoinColumn(name="Fk_ResumeId_Id")
	public Set<Experience> getExperiencesSet() {
		return experiencesSet;
	}

	public void setExperiencesSet(Set<Experience> experiencesSet) {
		this.experiencesSet = experiencesSet;
	}





}
