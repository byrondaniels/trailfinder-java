package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

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

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<HPHikesEntity> hPHikes;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<HikesEntity> hikes;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<CoursesEntity> courses;

	@OneToMany(mappedBy = "profileDetails", cascade = CascadeType.ALL)
	private List<SocialEntity> social;

	@Column
	private Date date;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
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

	public List<SocialEntity> getSocial() {
		return social;
	}

	public void setSocial(List<SocialEntity> social) {
		this.social = social;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
