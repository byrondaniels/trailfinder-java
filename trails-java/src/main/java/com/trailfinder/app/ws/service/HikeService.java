package com.trailfinder.app.ws.service;

import java.util.List;

import com.trailfinder.app.ws.shared.dto.HikesDto;


public interface HikeService {
	
	List<HikesDto> getHikes(String userId);
	
	HikesDto getHike(String addressId);
	
	void deleteHike(String addressId);
	
}