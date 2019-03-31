package com.funwork.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Schedule")
public class Schedule implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer scheduleId;
  private String scheduleName;
  private String color;
  private Timestamp endTime;
  private Timestamp startTime;
  private Float restHour;
  private Date workDate;
  private Interview interview;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Date getWorkDate() {
    return workDate;
  }

  public void setWorkDate(Date workDate) {
    this.workDate = workDate;
  }

  public String getScheduleName() {
    return scheduleName;
  }

  public void setScheduleName(String scheduleName) {
    this.scheduleName = scheduleName;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Float getRestHour() {
    return restHour;
  }

  public void setRestHour(Float restHour) {
    this.restHour = restHour;
  }

  /**
   * Get working hour.
   */
  @Transient
  public Float getWorkingHours() throws ParseException {
    SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm");
    long end = simpleFormat.parse(simpleFormat.format(getEndTime())).getTime();
    long start = simpleFormat.parse(simpleFormat.format(getStartTime())).getTime();
    long diff = (end - start);
    float days = diff / (1000 * 60 * 60 * 24);
    float hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    return hours - getRestHour();
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_Interview_Id")
  public Interview getInterview() {
    return interview;
  }

  public void setInterview(Interview interview) {
    this.interview = interview;
  }
}