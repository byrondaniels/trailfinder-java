package com.trailfinder.app.ws.io.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "posts")
public class PostsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7819806433130992635L;
	
	@Id // Marker for primary key
	@GeneratedValue
	private long id;

	@OneToOne
	@JoinColumn(name = "users_id")
	private UserEntity user;

	@Column(nullable = false)
	private String text;

	@Column
	private String name;

	@Column
	private String avatar;

	@OneToMany(mappedBy = "postDetails", cascade = CascadeType.ALL)
	private List<PostLikesEntity> likes;

	@OneToMany(mappedBy = "postDetails", cascade = CascadeType.ALL)
	private List<PostCommentsEntity> comments;

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

	public List<PostLikesEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<PostLikesEntity> likes) {
		this.likes = likes;
	}

	public List<PostCommentsEntity> getComments() {
		return comments;
	}

	public void setComments(List<PostCommentsEntity> comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
