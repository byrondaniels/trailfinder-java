package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "profiles")
public class ProfileEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7446565908182445933L;
	
	@Id // Marker for primary key
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String profileId;

	@Column(unique=true)
	private String user;

	@Column
	private String blog;

	@Column(nullable = false)
	private String location;

	@Column
	private String status;

	@Column(nullable = false)
	private String skills;

	@Column
	private String bio;

	@Column
	private String externalImg;
	
	@Column(length = 70)
	private String youtube;

	@Column(length = 70)
	private String twitter;

	@Column(length = 70)
	private String facebook;

	@Column(length = 70)
	private String linkedin;

	@Column(length = 70)
	private String instagram;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<HPHikesEntity> hPHikes;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<HikesEntity> hikes;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<CoursesEntity> courses;

	@Column
	private Date date;
	
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

	public List<HPHikesEntity> gethPHikes() {
		return hPHikes;
	}

	public void sethPHikes(List<HPHikesEntity> hPHikes) {
		this.hPHikes = hPHikes;
	}

	public List<HikesEntity> getHikes() {
		return hikes;
	}

	public void setHikes(List<HikesEntity> hikes) {
		this.hikes = hikes;
	}

	public List<CoursesEntity> getCourses() {
		return courses;
	}

	public void setCourses(List<CoursesEntity> courses) {
		this.courses = courses;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	

}
