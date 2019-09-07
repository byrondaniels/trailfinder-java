package com.trailfinder.app.ws.ui.model.response;

import org.springframework.hateoas.ResourceSupport;

public class HikesHPRest extends ResourceSupport {

	private String hikeHPId;
	private String hikeData;

	public String getHikeHPId() {
		return hikeHPId;
	}

	public void setHikeHPId(String hikeHPId) {
		this.hikeHPId = hikeHPId;
	}

	public String getHikeData() {
		return hikeData;
	}

	public void setHikeData(String hikeData) {
		this.hikeData = hikeData;
	}

}
