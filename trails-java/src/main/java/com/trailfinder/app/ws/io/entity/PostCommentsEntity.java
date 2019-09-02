package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "postComments")
public class PostCommentsEntity implements Serializable {

	private static final long serialVersionUID = 7809200551672852690L;
	
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "posts_id")
	private PostsEntity postDetails;

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

	public PostsEntity getPostDetails() {
		return postDetails;
	}

	public void setPostDetails(PostsEntity postDetails) {
		this.postDetails = postDetails;
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
