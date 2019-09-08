package com.trailfinder.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.HikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;
import com.trailfinder.app.ws.io.entity.UserEntity;
import com.trailfinder.app.ws.io.repositories.HikesRepository;
import com.trailfinder.app.ws.io.repositories.ProfileRepository;
import com.trailfinder.app.ws.io.repositories.UserRepository;
import com.trailfinder.app.ws.service.HikeService;
import com.trailfinder.app.ws.service.ProfileService;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.HikesDto;
import com.trailfinder.app.ws.shared.dto.ProfileDto;
import com.trailfinder.app.ws.ui.model.request.HikesHPRequestModel;
import com.trailfinder.app.ws.ui.model.request.HikesRequestModel;
import com.trailfinder.app.ws.ui.model.request.ProfileDetailsRequestModel;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;
import com.trailfinder.app.ws.ui.model.response.HikesRest;
import com.trailfinder.app.ws.ui.model.response.OperationStatusModel;
import com.trailfinder.app.ws.ui.model.response.ProfileRest;
import com.trailfinder.app.ws.ui.model.response.RequestOperationName;
import com.trailfinder.app.ws.ui.model.response.RequestOperationStatus;
import com.trailfinder.app.ws.ui.model.response.UnsplashRest;

@RestController
@RequestMapping("/profile") // Current path is: -> http://localhost:8080/profile
public class ProfileController {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	HikesRepository hikesRepository;

	@Autowired
	ProfileService profileService;

	@Autowired
	HikeService hikeService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	// Used to create a users profile
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest createProfile(@RequestBody ProfileDetailsRequestModel profileDetails,
			Authentication authentication) throws Exception {

		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();
		
		ProfileDto profile = modelMapper.map(profileDetails, ProfileDto.class);
		String loggedUserName =authentication.getName();

		ProfileDto profileDto = profileService.createProfile(profile,loggedUserName);
		returnValue = modelMapper.map(profileDto, ProfileRest.class);
		
		return returnValue;
	}

	// Used to update a users profile
	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest updateProfile(@RequestBody ProfileDetailsRequestModel profileDetails,
			Authentication authentication) {

		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();
		
		ProfileDto profile = modelMapper.map(profileDetails, ProfileDto.class);
		String loggedUserName = authentication.getName();

		ProfileDto profileDto = profileService.updateProfile(profile,loggedUserName);
		ProfileEntity createdProfile = modelMapper.map(profileDto, ProfileEntity.class);
		returnValue = modelMapper.map(createdProfile, ProfileRest.class);
		
		return returnValue;
	}

	// Used to get current users profile based on login authentication
	@GetMapping(path = "/me", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest getProfile(Authentication authentication) {

		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();

		UserEntity loggedUser = userRepository.findByEmail(authentication.getName());
		ProfileEntity currentProfile = profileRepository.findByUser(loggedUser.getUserId());

		returnValue = modelMapper.map(currentProfile, ProfileRest.class);

		return returnValue;
	}

	// Used to get a single profile from userId
	@GetMapping(path = "/user/{userId}", produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest getProfile(@PathVariable String userId) {

		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();

		ProfileEntity profile = profileRepository.findByUser(userId);

		if (profile == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		returnValue = modelMapper.map(profile, ProfileRest.class);

		return returnValue;
	}
	
	// Get list of all profiles
	@GetMapping(path = "/", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ProfileRest> getProfiles() {

		List<ProfileRest> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		Type listType = new TypeToken<List<ProfileRest>>() {}.getType();
		
		List<ProfileEntity> profiles = profileRepository.findAll();

		returnValue = modelMapper.map(profiles, listType);

		return returnValue;
	}

	// Used to delete current users profile
	@DeleteMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteProfile(Authentication authentication) {

		OperationStatusModel returnValue = new OperationStatusModel();

		returnValue.setOperationName(RequestOperationName.DELETE.name());
		UserEntity loggedUser = userRepository.findByEmail((String) authentication.getName());

		profileRepository.deleteByUser(loggedUser.getUserId());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

		return returnValue;
	}

	// Used to add a hike to users profile
	@PutMapping(path = "/hikes", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest addHikes(@RequestBody HikesRequestModel hikeDetails, Authentication authentication) {
		
		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();
		ProfileDto profileDto = new ProfileDto();

		UserEntity loggedUser = userRepository.findByEmail((String) authentication.getName());
		ProfileEntity currentProfile = profileRepository.findByUser(loggedUser.getUserId());

		profileDto = modelMapper.map(currentProfile, ProfileDto.class);
		ProfileDto updatedProfile = profileService.updateProfileByHike(profileDto, hikeDetails);
		returnValue = modelMapper.map(updatedProfile, ProfileRest.class);

		return returnValue;
	}

	// Get hikes by logged in user
	@GetMapping(path = "/hikes/me", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public Resources<HikesRest> getHikesByProfile(Authentication authentication) {

		List<HikesRest> hikesListRestModel = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity loggedUser = userRepository.findByEmail((String) authentication.getName());
		String id = loggedUser.getUserId();

		List<HikesDto> hikesDto = profileService.getHikesByUser(id);

		if (hikesDto != null && !hikesDto.isEmpty()) {
			Type listType = new TypeToken<List<HikesRest>>() {}.getType();
			hikesListRestModel = modelMapper.map(hikesDto, listType);

			for (HikesRest hikesRest : hikesListRestModel) {
				Link hikeLink = linkTo(methodOn(ProfileController.class)
						.getHikeByProfile(id, hikesRest.getHikeId()))
						.withSelfRel();
				hikesRest.add(hikeLink);

				Link profileLink = linkTo(methodOn(ProfileController.class)
						.getProfile(id))
						.withRel("profile");
				hikesRest.add(profileLink);
			}
		}
		return new Resources<>(hikesListRestModel);
	}

	// Get hikes by profileId
	@GetMapping(path = "/hikes", produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public Resources<HikesRest> getHikesByProfile(@RequestBody String profileId) {

		List<HikesRest> hikesListRestModel = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		List<HikesDto> hikesDto = profileService.getHikesByUser(profileId);

		if (hikesDto != null && !hikesDto.isEmpty()) {
			Type listType = new TypeToken<List<HikesRest>>() {}.getType();
			hikesListRestModel = modelMapper.map(hikesDto, listType);

			for (HikesRest hikesRest : hikesListRestModel) {
				Link hikeLink = linkTo(methodOn(ProfileController.class)
						.getHikeByProfile(profileId, hikesRest.getHikeId()))
						.withSelfRel();
				hikesRest.add(hikeLink);

				Link profileLink = linkTo(methodOn(ProfileController.class)
						.getProfile(profileId))
						.withRel("profile");
				hikesRest.add(profileLink);
			}
		}
		return new Resources<>(hikesListRestModel);
	}

	// The below method is for testing and not needed for production
	@GetMapping(path = "/hikes/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<HikesRest> getAllHikes() {

		List<HikesRest> returnValue = new ArrayList<>();
		List<HikesEntity> hikes = hikesRepository.findAll();

		Type listType = new TypeToken<List<HikesRest>>() {}.getType();
		returnValue = new ModelMapper().map(hikes, listType);

		return returnValue;
	}
	// end of method

	// The below method gets a hike based on profile id & hike id
	@GetMapping(path = "/{profileId}/hikes/{hikeId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, "application/hal+json" })
	public Resource<HikesRest> getHikeByProfile(@PathVariable String profileId, @PathVariable String hikeId) {

		ModelMapper modelMapper = new ModelMapper();
		
		HikesDto hikesDto = hikeService.getHike(hikeId);

		Link hikeLink = linkTo(methodOn(ProfileController.class).getHikeByProfile(profileId, hikeId)).withSelfRel();
		Link profileLink = linkTo(UserController.class).slash(profileId).withRel("profile");
		Link hikesLink = linkTo(methodOn(ProfileController.class).getHikesByProfile(profileId)).withRel("hikes");

		HikesRest addressesRestModel = modelMapper.map(hikesDto, HikesRest.class);

		addressesRestModel.add(hikeLink);
		addressesRestModel.add(profileLink);
		addressesRestModel.add(hikesLink);

		return new Resource<>(addressesRestModel);
	}

	// The following is used to delete hike from user profile
	@DeleteMapping(path = "/hikes/{hikeId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteHike(@PathVariable String hikeId) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());

		hikeService.deleteHike(hikeId);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}

	// Used to add a hike from HikeProject API call (client side call) to users profile
	@PutMapping(path = "/APIhikes", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest addHPHikes(@RequestBody HikesHPRequestModel hikeDetails, Authentication authentication) {

		ProfileRest returnValue = new ProfileRest();
		ModelMapper modelMapper = new ModelMapper();
		ProfileDto profileDto = new ProfileDto();

		UserEntity loggedUser = userRepository.findByEmail((String) authentication.getName());
		ProfileEntity currentProfile = profileRepository.findByUser(loggedUser.getUserId());
		profileDto = modelMapper.map(currentProfile, ProfileDto.class);
		ProfileDto updatedProfile = profileService.updateProfileByHPHike(profileDto, hikeDetails);
		returnValue = modelMapper.map(updatedProfile, ProfileRest.class);

		return returnValue;
	}

	@DeleteMapping(path = "/APIhikes/{hike_id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest deleteHPHikes() {
		// Used to delete API hike from user profile

		return null;
	}

	@PutMapping(path = "/courses", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest addCourse() {
		// Used to add a course to users profile

		return null;
	}

	@DeleteMapping(path = "/courses/{course_id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ProfileRest deleteCourse() {
		// Used to delete a course from user profile

		return null;
	}

	@GetMapping(path = "/unsplash/{subject}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public UnsplashRest getUnsplashBySubject() {
		// This will require making an API call to unsplash from a subject keyword
		return null;
	}

}
