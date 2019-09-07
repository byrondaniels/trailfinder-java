package com.trailfinder.app.ws.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.UserEntity;
import com.trailfinder.app.ws.io.repositories.UserRepository;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.UserDto;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity =userRepository.findByEmail(email);
		if(userEntity ==null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),new ArrayList<>());
	}

	@Override
	public UserDto createUser(UserDto user) {

		if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record already exists");
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		
		String publicUserId= utils.generateId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		
        Timestamp ts=new Timestamp(System.currentTimeMillis());  
		userEntity.setDate(new Date(ts.getTime()));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
 		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity =userRepository.findByEmail(email);
		UserDto returnValue =modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) 
			throw new UsernameNotFoundException("User with ID: "+userId+" cannot be found");
		
		UserDto returnValue =modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Transactional
	@Override
	public void deleteUser(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userRepository.delete(userEntity);
	}

}
