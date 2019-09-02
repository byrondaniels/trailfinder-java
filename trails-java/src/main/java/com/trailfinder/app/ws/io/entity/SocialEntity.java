package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "social")
public class SocialEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -528038767955819850L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "profiles_id")
	private ProfileEntity profileDetails;

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
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProfileEntity getProfileDetails() {
		return profileDetails;
	}

	public void setProfileDetaila(ProfileEntity profileDetails) {
		this.profileDetails = profileDetails;
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

}
