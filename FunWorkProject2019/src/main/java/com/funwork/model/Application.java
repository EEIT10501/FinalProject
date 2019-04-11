package com.funwork.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
public class Application {
  private Integer applicationId;
  private String answer;
  private Timestamp applicationTime;
  private String appliedStatus;
  private String latestMsg;
  private Timestamp latestMsgTime;
  private User user;
  @JsonIgnoreProperties({ "jobCompany" })
  private Job job;

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

  public Timestamp getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(Timestamp applicationTime) {
    this.applicationTime = applicationTime;
  }

  @Column(columnDefinition = "nvarchar(255) default '未邀約' ")
  public String getAppliedStatus() {
    return appliedStatus;
  }

  public void setAppliedStatus(String appliedStatus) {
    this.appliedStatus = appliedStatus;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_User_Id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_Job_Id")
  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getLatestMsg() {
    return latestMsg;
  }

  public void setLatestMsg(String latestMsg) {
    this.latestMsg = latestMsg;
  }

  public Timestamp getLatestMsgTime() {
    return latestMsgTime;
  }

  public void setLatestMsgTime(Timestamp latestMsgTime) {
    this.latestMsgTime = latestMsgTime;
  }
}