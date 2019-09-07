package com.trailfinder.app.ws.ui.model.request;

import java.util.List;

public class ProfileDetailsRequestModel {

	private String user="";
	private String blog="";
	private String location="";
	private String status="";
	private String skills="";
	private String bio="";
	private String externalImg="";
	private String youtube="";
	private String facebook="";
	private String twitter="";
	private String instagram="";
	private String linkedin="";
	private List<HikesRequestModel> hikes;
	private List<HikesHPRequestModel> hikesHP;
	private List<CourseRequestModel> courses;

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

	public void setCourses(List<CourseRequestModel> courses) {
		this.courses = courses;
	}

	public List<HikesRequestModel> getHikes() {
		return hikes;
	}

	public void setHikes(List<HikesRequestModel> hikes) {
		this.hikes = hikes;
	}

	public List<HikesHPRequestModel> getHikesHP() {
		return hikesHP;
	}

	public void setHikesHP(List<HikesHPRequestModel> hikesHP) {
		this.hikesHP = hikesHP;
	}

	public List<CourseRequestModel> getCourses() {
		return courses;
	}

	public void setCourse(List<CourseRequestModel> courses) {
		this.courses = courses;
	}

}
