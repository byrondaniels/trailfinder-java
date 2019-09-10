package com.trailfinder.app.ws.service;

import java.util.List;

import com.trailfinder.app.ws.shared.dto.HikesDto;
import com.trailfinder.app.ws.shared.dto.HikesHPDto;
import com.trailfinder.app.ws.shared.dto.ProfileDto;
import com.trailfinder.app.ws.ui.model.request.HikesHPRequestModel;
import com.trailfinder.app.ws.ui.model.request.HikesRequestModel;

public interface ProfileService {
	
	ProfileDto createProfile(ProfileDto profile, String authenticatedName);
	
	ProfileDto updateProfile(ProfileDto initialProfile,String loggedUserName);
	
	ProfileDto updateProfileByHike(ProfileDto profileDto,HikesRequestModel hikeDetails );
	
	ProfileDto updateProfileByHPHike(ProfileDto profileDto,HikesHPRequestModel hikeDetails );
	
	ProfileDto getProfileByUser(String user);
	
	List<HikesDto> getHikesByUser(String userId);
	
	List<HikesHPDto> getHPHikesByUser(String userId);

}

