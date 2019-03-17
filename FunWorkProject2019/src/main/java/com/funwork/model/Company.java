package com.funwork.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

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
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@DynamicInsert
@Entity
@Table(name = "Company")
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer companyId;
	private String name;
	private String taxId;
	private String address;
	@JsonIgnore
	private Blob licensure;
	private String reviewStatus;
	private Integer notificationTimes;
	@JsonIgnore
	private Blob logo;
	@JsonIgnore
	private Blob coverPic;
	@JsonIgnore
	private Clob description;
	private String siteURL;
	private String fileName;

	@JsonIgnore
	private MultipartFile companyLicensureImage;
	@JsonIgnore
	private MultipartFile companyLogo;
	@JsonIgnore
	private MultipartFile companyCoverPic;
	
    @XmlTransient
    @Transient
	public MultipartFile getCompanyLicensureImage() {
		return companyLicensureImage;
	}

	public void setCompanyLicensureImage(MultipartFile companyLicensureImage) {
		this.companyLicensureImage = companyLicensureImage;
	}
	
	@XmlTransient
    @Transient
	public MultipartFile getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(MultipartFile companyLogo) {
		this.companyLogo = companyLogo;
	}

	@XmlTransient
    @Transient
	public MultipartFile getCompanyCoverPic() {
		return companyCoverPic;
	}

	public void setCompanyCoverPic(MultipartFile companyCoverPic) {
		this.companyCoverPic = companyCoverPic;
	}



	@JsonIgnore
	private User user;

	public Company() {
	}
	
	

	public Company(Integer companyId, String name, String taxId, String address, Blob licensure, String reviewStatus,
			Integer notificationTimes, Blob logo, Blob coverPic, Clob description, String siteURL, String fileName,
			MultipartFile companyLicensureImage,MultipartFile companyLogo,MultipartFile companyCoverPic,String forRecordColumn) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.taxId = taxId;
		this.address = address;
		this.licensure = licensure;
		this.reviewStatus = reviewStatus;
		this.notificationTimes = notificationTimes;
		this.logo = logo;
		this.coverPic = coverPic;
		this.description = description;
		this.siteURL = siteURL;
		this.fileName = fileName;
		this.companyLicensureImage = companyLicensureImage;
		this.companyLogo = companyLogo;
		this.coverPic = coverPic;
	}



	public Company(Integer companyId, String name, String taxId, String address, Blob licensure, String reviewStatus,
			Integer notificationTimes, Blob logo, Blob coverPic, Clob description, String siteURL, String fileName,
			MultipartFile companyLicensureImage, MultipartFile companyLogo,MultipartFile companyCoverPic, User user) {
		super();
		this.companyId = companyId;
		this.name = name;
		this.taxId = taxId;
		this.address = address;
		this.licensure = licensure;
		this.reviewStatus = reviewStatus;
		this.notificationTimes = notificationTimes;
		this.logo = logo;
		this.coverPic = coverPic;
		this.description = description;
		this.siteURL = siteURL;
		this.fileName = fileName;
		this.companyLicensureImage = companyLicensureImage;
		this.companyLogo = companyLogo;
		this.coverPic = coverPic;
		this.user = user;
	}

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

	@Column(nullable = false, unique = true)
	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	
	@Column(nullable = false)
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

	@Column(columnDefinition = "nvarchar(255) default '審查中' ")
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

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

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

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", name=" + name + ", taxId=" + taxId + ", address=" + address
				+ ", licensure=" + licensure + ", reviewStatus=" + reviewStatus + ", notificationTimes="
				+ notificationTimes + ", coverPic=" + coverPic + ", description=" + description + ", logo=" + logo
				+ ", siteURL=" + siteURL + ", userId=" + user.getUserId() + "]";
	}
}
