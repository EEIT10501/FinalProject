package com.funwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

@DynamicInsert
@Entity
@Table(name = "Company")
public class Company implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer companyId;
  private String name;
  private String taxId;
  private String address;
  @JsonIgnore
  private Blob licensure;
  private String reviewStatus;
  private Integer notificationTimes;
  private Timestamp submitTime;
  private Timestamp reviewTime;
  private String failReason;
  @JsonIgnore
  private Blob logo;
  @JsonIgnore
  private Blob coverPic;
  @JsonIgnore
  private Clob description;
  private String siteUrl;
  private String fileName;

  @JsonIgnore
  private MultipartFile companyLicensureImage;
  @JsonIgnore
  private MultipartFile companyLogo;
  @JsonIgnore
  private MultipartFile companyCoverPic;

  @Transient
  public MultipartFile getCompanyLicensureImage() {
    return companyLicensureImage;
  }

  public void setCompanyLicensureImage(MultipartFile companyLicensureImage) {
    this.companyLicensureImage = companyLicensureImage;
  }

  @Transient
  public MultipartFile getCompanyLogo() {
    return companyLogo;
  }

  public void setCompanyLogo(MultipartFile companyLogo) {
    this.companyLogo = companyLogo;
  }

  @Transient
  public MultipartFile getCompanyCoverPic() {
    return companyCoverPic;
  }

  public void setCompanyCoverPic(MultipartFile companyCoverPic) {
    this.companyCoverPic = companyCoverPic;
  }

  @JsonIgnore
  private User user;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  @Column(nullable = false, columnDefinition = "nvarchar(255)")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(nullable = false, unique = true,columnDefinition = "nvarchar(255)")
  public String getTaxId() {
    return taxId;
  }

  public void setTaxId(String taxId) {
    this.taxId = taxId;
  }

  @Column(nullable = false,columnDefinition = "nvarchar(255)")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Blob getLicensure() {
    return licensure;
  }

  public void setLicensure(Blob licensure) {
    this.licensure = licensure;
  }

  @Column(columnDefinition = "nvarchar(255) default '待審核' ")
  public String getReviewStatus() {
    return reviewStatus;
  }

  public void setReviewStatus(String reviewStatus) {
    this.reviewStatus = reviewStatus;
  }

  @Column(nullable = true, columnDefinition = "int default 3")
  public Integer getNotificationTimes() {
    return notificationTimes;
  }

  public void setNotificationTimes(Integer notificationTimes) {
    this.notificationTimes = notificationTimes;
  }

  public Blob getCoverPic() {
    return coverPic;
  }

  public void setCoverPic(Blob coverPic) {
    this.coverPic = coverPic;
  }

  public Clob getDescription() {
    return description;
  }

  public void setDescription(Clob description) {
    this.description = description;
  }

  public Blob getLogo() {
    return logo;
  }

  public void setLogo(Blob logo) {
    this.logo = logo;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getSiteUrl() {
    return siteUrl;
  }

  public void setSiteUrl(String siteUrl) {
    this.siteUrl = siteUrl;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "Fk_User_Id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(Timestamp submitTime) {
    this.submitTime = submitTime;
  }

  public Timestamp getReviewTime() {
    return reviewTime;
  }

  public void setReviewTime(Timestamp reviewTime) {
    this.reviewTime = reviewTime;
  }

  @Column(columnDefinition = "nvarchar(255)")
  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }
}