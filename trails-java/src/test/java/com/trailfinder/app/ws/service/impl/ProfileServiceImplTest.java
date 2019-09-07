package com.trailfinder.app.ws.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trailfinder.app.ws.io.entity.ProfileEntity;
import com.trailfinder.app.ws.io.repositories.ProfileRepository;
import com.trailfinder.app.ws.service.ProfileServiceImpl;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.ProfileDto;
import com.trailfinder.app.ws.ui.model.request.HikesRequestModel;

public class ProfileServiceImplTest {
	
	@InjectMocks
	ProfileServiceImpl profileService;
	
	ProfileDto profileDto;
	ProfileEntity profileEntity;
	
	HikesRequestModel hikesRequestModel;
	
	@Mock
	ProfileRepository profileRepository;
	
	@Mock
	Utils utils;
	
	String hikeId = "hhty57ehfy";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		profileEntity = new ProfileEntity();
		profileEntity.setId(1L);
		profileEntity.setLocation("Calgary");
		profileEntity.setProfileId("123432s");
		profileEntity.setUser("3e2wad33");
		profileEntity.setBlog("hike");
		profileEntity.setStatus("Beg");
		profileEntity.setSkills("Hike");
		profileEntity.setBio("Fun");
		
	}
	
	@Test
	final void testUpdateProfileByHike() {
		hikesRequestModel = new HikesRequestModel();
		hikesRequestModel.setStatus("Comp");
		hikesRequestModel.setLocation("Fiji");
		hikesRequestModel.setName("WCT");
		
		profileDto = new ProfileDto();
		profileDto.setId(1L);
		profileDto.setLocation("Calgary");
		profileDto.setProfileId("123432s");
		profileDto.setUser("3e2wad33");
		profileDto.setBlog("hike");
		profileDto.setStatus("Beg");
		profileDto.setSkills("Hike");
		profileDto.setBio("Fun");
		
		when(utils.generateId(anyInt())).thenReturn(hikeId);
		when(profileRepository.save(any(ProfileEntity.class))).thenReturn(profileEntity);
		
		ProfileDto updatedProfile= profileService.updateProfileByHike(profileDto, hikesRequestModel);
		assertNotNull(updatedProfile);
		assertEquals(updatedProfile.getLocation(), profileDto.getLocation());
		assertEquals(updatedProfile.getHikes().size(),1);
		verify(profileRepository,times(1)).save(any(ProfileEntity.class));
		
	}

}
