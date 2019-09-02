package com.trailfinder.app.ws.ui.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.service.UserService;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.UserDto;
import com.trailfinder.app.ws.ui.model.request.UserDetailsRequestModel;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;
import com.trailfinder.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	Utils utils;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserRest returnValue = new UserRest();

		if (userDetails.getName().isEmpty() || userDetails.getEmail().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		if (!Objects.equals(userDetails.getPassword(), userDetails.getPassword2())){
			throw new UserServiceException(ErrorMessages.PASSWORDS_DONT_MATCH.getErrorMessage());
		}
		if (userDetails.getPassword().length()>6){
			throw new UserServiceException(ErrorMessages.PASSWORD_INVALID_LENGTH.getErrorMessage());
		}
		if(!utils.validateEmail(userDetails.getEmail())) {
			throw new UserServiceException(ErrorMessages.INVALID_ENTRY_EMAIL.getErrorMessage());
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		return returnValue;

	}

}
