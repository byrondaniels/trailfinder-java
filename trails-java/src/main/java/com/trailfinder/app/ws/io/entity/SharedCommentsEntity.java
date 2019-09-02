package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sharedComments")
public class SharedCommentsEntity implements Serializable {

	private static final long serialVersionUID = 7809232323672852690L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "shared_id")
	private SharedEntity sharedDetails;

	@Column
	private UserEntity user;

	@Column(nullable = false)
	private String text;

	@Column(length = 50)
	private String name;

	@Column
	private String avatar;

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

	public SharedEntity getSharedDetails() {
		return sharedDetails;
	}

	public void setSharedDetails(SharedEntity sharedDetails) {
		this.sharedDetails = sharedDetails;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
