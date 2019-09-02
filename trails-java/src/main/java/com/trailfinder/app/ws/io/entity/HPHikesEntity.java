package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "HPhikes")
public class HPHikesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4564835309451690679L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "profiles_id")
	private ProfileEntity profileDetails;

	@Column(nullable = false)
	private String hikeData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProfileEntity getProfileDetails() {
		return profileDetails;
	}

	public void setProfile(ProfileEntity profileDetails) {
		this.profileDetails = profileDetails;
	}

	public String getHikeData() {
		return hikeData;
	}

	public void setHikeData(String hikeData) {
		this.hikeData = hikeData;
	}

}
