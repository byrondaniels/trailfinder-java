package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "postLikes")
public class PostLikesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1848360466414521728L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "posts_id")
	private PostsEntity postDetails;

	@Column(nullable = false)
	private UserEntity user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PostsEntity getPostDetails() {
		return postDetails;
	}

	public void setPostDetails(PostsEntity postDetails) {
		this.postDetails = postDetails;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
