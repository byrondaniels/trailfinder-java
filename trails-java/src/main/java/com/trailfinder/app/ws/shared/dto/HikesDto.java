package com.trailfinder.app.ws.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class HikesDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4919661524620779719L;
	private long id;
	private String hikeId;
	private String name;
	private String location;
	private String length;
	private Date fromDate;
	private Date toDate;
	private String description;
	private String status;
	private ProfileDto profileDetails;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHikeId() {
		return hikeId;
	}

	public void setHikeId(String hikeId) {
		this.hikeId = hikeId;
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

	public ProfileDto getProfileDetails() {
		return profileDetails;
	}

	public void setProfileDetails(ProfileDto profileDetails) {
		this.profileDetails = profileDetails;
	}

}
