package com.trailfinder.app.ws.shared.dto;


public class HikesHPDto {

	private long id;
	private ProfileDto profileDetails;
	private String hikeData;
	private String hPHikeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProfileDto getProfile() {
		return profileDetails;
	}

	public void setProfile(ProfileDto profileDetails) {
		this.profileDetails = profileDetails;
	}

	public String getHikeData() {
		return hikeData;
	}

	public void setHikeData(String hikeData) {
		this.hikeData = hikeData;
	}

	public String getHPHikeId() {
		return hPHikeId;
	}

	public void setHPHikeId(String hPHikeId) {
		this.hPHikeId = hPHikeId;
	}

}
