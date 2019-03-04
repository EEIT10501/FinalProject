package com.funwork.model;

import java.sql.Blob;
import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Yang Cheng User 和 Company 做關聯性 (雙向多對一) One user corresponds to
 *         multiple companies while one company corresponds to one user
 */

//@DynamicInsert(value=true)
@Entity
@Table(name = "Company")
public class Company {
	private Integer companyId;
	private String name;
	private String taxId;
	private String address;
	@JsonIgnore
	private Blob licensure;
	private Integer reviewStatus;
	private Integer notificationTimes;
	@JsonIgnore
	private Blob logo;
	@JsonIgnore
	private Blob coverPic;
	private Clob description;
	private String siteURL;
	private String fileName;
	
	@JsonIgnore
	private MultipartFile companylicensure;
	
    @XmlTransient
    @Transient
	public MultipartFile getcompanylicensure() {
		return companylicensure;
	}

	public void setProductImage(MultipartFile companylicensure) {
		this.companylicensure = companylicensure;
	}
	
	//以下為儲存多方的實例變數

	// 以下為儲存多方的實例變數
	Set<Job> jobsSet = new HashSet<>();

	// 通知Hibernate以此參考設定外鍵欄位
	private User user;

	public Company() {
	}

	public Company(Integer companyId, String name, String taxId, String address, Blob licensure, Integer reviewStatus,
			User user) {
		this.companyId = companyId;
		this.name = name;
		this.taxId = taxId;
		this.address = address;
		this.licensure = licensure;
		this.reviewStatus = reviewStatus;
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

	@Column(nullable = false, columnDefinition = "nvarchar(255)")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = true)
	public Blob getLicensure() {
		return licensure;
	}

	public void setLicensure(Blob licensure) {
		this.licensure = licensure;
	}

	@Column(nullable = false, columnDefinition = "int default 0")
	public Integer getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(Integer reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	@Column(nullable = false, columnDefinition = "int default 3")
	public Integer getNotificationTimes() {
		return notificationTimes;
	}

	public void setNotificationTimes(Integer notificationTimes) {
		this.notificationTimes = notificationTimes;
	}

	@Column(nullable = true)
	public Blob getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(Blob coverPic) {
		this.coverPic = coverPic;
	}

	@Column(nullable = true)
	public Clob getDescription() {
		return description;
	}

	public void setDescription(Clob description) {
		this.description = description;
	}

	@Column(nullable = true)
	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	@Column(nullable = true)
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


	// Company 類別並沒有表示關聯的資訊 , 此資訊位於 Job 的 jobCompany 性質中
	@OneToMany(mappedBy = "jobCompany", cascade = CascadeType.ALL)
	public Set<Job> getJobsSet() {
		return jobsSet;
	}

	public void setJobsSet(Set<Job> jobsSet) {
		this.jobsSet = jobsSet;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user_Id")
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
