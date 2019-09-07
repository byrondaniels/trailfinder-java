package com.trailfinder.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -591406052483434857L;
	private long id;
	private String profileId;
	private String user;
	private String blog;
	private String location;
	private String status;
	private String skills;
	private String bio;
	private String externalImg;
	private String youtube;
	private String facebook;
	private String twitter;
	private String instagram;
	private String linkedin;
	private List<HikesDto> hikes;
	private List<HikesHPDto> hikesHP;
	private List<CoursesDto> courses;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getExternalImg() {
		return externalImg;
	}

	public void setExternalImg(String externalImg) {
		this.externalImg = externalImg;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public List<HikesDto> getHikes() {
		return hikes;
	}

	public void setHikes(List<HikesDto> hikes) {
		this.hikes = hikes;
	}

	public List<HikesHPDto> getHikesHP() {
		return hikesHP;
	}

	public void setHikesHP(List<HikesHPDto> hikesHP) {
		this.hikesHP = hikesHP;
	}

	public List<CoursesDto> getCourses() {
		return courses;
	}

	public void setCourses(List<CoursesDto> courses) {
		this.courses = courses;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

}
