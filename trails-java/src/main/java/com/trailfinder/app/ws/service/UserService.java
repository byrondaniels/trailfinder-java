package com.trailfinder.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.trailfinder.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	UserDto createUser(UserDto user);
	
	UserDto getUser(String email);
	
	UserDto getUserByUserId(String userId);
	
	String getUserIdFromEmail(String email);
	
	void deleteUser(String userId);
	
}
