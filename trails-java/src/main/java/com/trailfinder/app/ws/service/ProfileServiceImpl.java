package com.trailfinder.app.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.HikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;
import com.trailfinder.app.ws.io.entity.UserEntity;
import com.trailfinder.app.ws.io.repositories.HikesRepository;
import com.trailfinder.app.ws.io.repositories.ProfileRepository;
import com.trailfinder.app.ws.io.repositories.UserRepository;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.HikesDto;
import com.trailfinder.app.ws.shared.dto.HikesHPDto;
import com.trailfinder.app.ws.shared.dto.ProfileDto;
import com.trailfinder.app.ws.ui.model.request.HikesHPRequestModel;
import com.trailfinder.app.ws.ui.model.request.HikesRequestModel;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	Utils utils;
	
	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HikesRepository hikesRepository;

	public ProfileDto updateProfileByHike(ProfileDto currentProfile, HikesRequestModel hikeDetails) {
		ModelMapper modelMapper = new ModelMapper();
		
		HikesDto hikesDto = modelMapper.map(hikeDetails, HikesDto.class);
		hikesDto.setProfileDetails(currentProfile);
		hikesDto.setHikeId(utils.generateId(30));
		currentProfile.getHikes().add(hikesDto);
		
		ProfileEntity profileEntity = modelMapper.map(currentProfile, ProfileEntity.class);
		ProfileEntity updatedProfile = profileRepository.save(profileEntity);
		
		ProfileDto returnValue = modelMapper.map(updatedProfile, ProfileDto.class);
		return returnValue;
	}
	
	public ProfileDto updateProfileByHPHike(ProfileDto currentProfile, HikesHPRequestModel hikeDetails) {
		ModelMapper modelMapper = new ModelMapper();
		
		HikesHPDto hikesHPDto = modelMapper.map(hikeDetails, HikesHPDto.class);
		hikesHPDto.setProfile(currentProfile);
		hikesHPDto.setHPHikeId(utils.generateId(30));
		currentProfile.getHikesHP().add(hikesHPDto);
		
		ProfileEntity profileEntity = modelMapper.map(currentProfile, ProfileEntity.class);
		ProfileEntity updatedProfile = profileRepository.save(profileEntity);
		
		ProfileDto returnValue = modelMapper.map(updatedProfile, ProfileDto.class);
		return returnValue;
		
	}
	
	@Override
	public List<HikesDto> getHikesByUser(String userId){
        List<HikesDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        
        ProfileEntity profileEntity = profileRepository.findByUser(userId).get(0);
        
        if(profileEntity==null) {
			throw new UserServiceException(ErrorMessages.COULD_NOT_FIND_PROFILE.getErrorMessage());
		}
 
        Iterable<HikesEntity> hikes = hikesRepository.findAllByProfileDetails(profileEntity);
        
        if(hikes==null) return returnValue;
        
        for(HikesEntity hikesEntity:hikes){
            returnValue.add( modelMapper.map(hikesEntity, HikesDto.class) );
        }
        
        return returnValue;
	}

	@Override
	public ProfileDto createProfile(ProfileDto profileDetails, String loggedUserName) {
        ModelMapper modelMapper = new ModelMapper();
		
		if (profileDetails.getLocation().isEmpty() || profileDetails.getSkills().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}

		UserEntity loggedUser = userRepository.findByEmail(loggedUserName);
		Integer found = profileRepository.findByUser(loggedUser.getUserId()).size();
		
		if (found > 0)
			throw new UserServiceException(ErrorMessages.PROFILE_ALREADY_CREATED.getErrorMessage());

		profileDetails.setUser(loggedUser.getUserId());
		profileDetails.setProfileId(utils.generateId(30));
		ProfileEntity profileEntity = modelMapper.map(profileDetails, ProfileEntity.class);
		ProfileEntity createdProfile = profileRepository.save(profileEntity);
		
		ProfileDto returnValue = modelMapper.map(createdProfile, ProfileDto.class);
		return returnValue;
	}

	@Override
	public ProfileDto updateProfile(ProfileDto profile, String loggedUserName) {
		
		UserEntity loggedUser = userRepository.findByEmail(loggedUserName);
		ProfileEntity currentProfile = profileRepository.findByUser(loggedUser.getUserId()).get(0);
		
		if (currentProfile == null)
			throw new UserServiceException(ErrorMessages.COULD_NOT_FIND_PROFILE.getErrorMessage());

		if (profile.getBlog() != "")
			currentProfile.setBlog(profile.getBlog());
		if (profile.getLocation() != "")
			currentProfile.setLocation(profile.getLocation());
		if (profile.getStatus() != "")
			currentProfile.setStatus(profile.getStatus());
		if (profile.getSkills() != "")
			currentProfile.setSkills(profile.getSkills());
		if (profile.getBio() != "")
			currentProfile.setBio(profile.getBio());
		if (profile.getExternalImg() != "")
			currentProfile.setExternalImg(profile.getExternalImg());
		if (profile.getYoutube() != "")
			currentProfile.setYoutube(profile.getYoutube());
		if (profile.getLinkedin() != "")
			currentProfile.setLinkedin(profile.getLinkedin());
		if (profile.getFacebook() != "")
			currentProfile.setFacebook(profile.getFacebook());
		if (profile.getTwitter() != "")
			currentProfile.setTwitter(profile.getTwitter());
		if (profile.getInstagram() != "")
			currentProfile.setInstagram(profile.getInstagram());
		
		ProfileEntity updatedProfileDetails = profileRepository.save(currentProfile);
		
		ProfileDto returnValue = new ModelMapper().map(updatedProfileDetails, ProfileDto.class);
		return returnValue;
	}
	
}
