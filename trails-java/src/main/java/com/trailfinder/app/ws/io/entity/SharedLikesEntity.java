package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sharedLikes")
public class SharedLikesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8818442090084665109L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "shared_id")
	private SharedEntity sharedDetails;

	@Column
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SharedEntity getSharedDetails() {
		return sharedDetails;
	}

	public void setSharedDetails(SharedEntity sharedDetails) {
		this.sharedDetails = sharedDetails;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	

}
