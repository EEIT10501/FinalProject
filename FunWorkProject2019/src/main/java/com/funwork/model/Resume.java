package com.funwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Resume {
  private Integer resumeId;
  private String name;
  private String phoneNum;
  private Date birth;
  private String educationLevel;
  private Blob profilePic;
  private String selfIntro;
  private String fileName;
  private String gender;
  private User user;
  private String type1;
  private String position1;
  private String term1;
  private String type2;
  private String position2;
  private String term2;
  @JsonIgnore
  private MultipartFile profilePart;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getResumeId() {
    return resumeId;
  }

  public void setResumeId(Integer resumeId) {
    this.resumeId = resumeId;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date bitrh) {
    this.birth = bitrh;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getEducationLevel() {
    return educationLevel;
  }

  public void setEducationLevel(String educationLevel) {
    this.educationLevel = educationLevel;
  }

  public Blob getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(Blob profilePic) {
    this.profilePic = profilePic;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getSelfIntro() {
    return selfIntro;
  }

  public void setSelfIntro(String selfIntro) {
    this.selfIntro = selfIntro;
  }

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_User_Id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Transient
  public MultipartFile getProfilePart() {
    return profilePart;
  }

  public void setProfilePart(MultipartFile profilePart) {
    this.profilePart = profilePart;
  }

  public String getType1() {
    return type1;
  }

  public void setType1(String type1) {
    this.type1 = type1;
  }

  public String getPosition1() {
    return position1;
  }

  public void setPosition1(String position1) {
    this.position1 = position1;
  }

  public String getTerm1() {
    return term1;
  }

  public void setTerm1(String term1) {
    this.term1 = term1;
  }

  public String getType2() {
    return type2;
  }

  public void setType2(String type2) {
    this.type2 = type2;
  }

  public String getPosition2() {
    return position2;
  }

  public void setPosition2(String position2) {
    this.position2 = position2;
  }

  public String getTerm2() {
    return term2;
  }

  public void setTerm2(String term2) {
    this.term2 = term2;
  }
}