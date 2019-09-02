package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "hikes")
public class HikesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2696178329246561673L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "profiles_id")
	private ProfileEntity profileDetails;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String location;

	@Column
	private String length;

	@Column
	private Date fromDate;

	@Column
	private Date toDate;

	@Column
	private String description;

	@Column(nullable = false)
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProfileEntity getProfileDetails() {
		return profileDetails;
	}

	public void setProfileDetails(ProfileEntity profileDetails) {
		this.profileDetails = profileDetails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
