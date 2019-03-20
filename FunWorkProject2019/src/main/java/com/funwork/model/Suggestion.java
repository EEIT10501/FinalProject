package com.funwork.model;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suggestion {
  private Integer suggestionId;
  private String comment;
  private String email;
  private Timestamp submitTime;
  private Blob attachment;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getSuggestionId() {
    return suggestionId;
  }

  public void setSuggestionId(Integer suggestionId) {
    this.suggestionId = suggestionId;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }

  public Blob getAttachment() {
    return attachment;
  }

  public void setAttachment(Blob attachment) {
    this.attachment = attachment;

  }

}
