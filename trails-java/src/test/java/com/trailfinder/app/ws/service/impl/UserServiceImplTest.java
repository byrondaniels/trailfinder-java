package com.trailfinder.app.ws.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.UserEntity;
import com.trailfinder.app.ws.io.repositories.UserRepository;
import com.trailfinder.app.ws.service.UserServiceImpl;
import com.trailfinder.app.ws.shared.Utils;
import com.trailfinder.app.ws.shared.dto.UserDto;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;
 
	@Mock
	Utils utils;
	
	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
 
	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";
	
	UserEntity userEntity;
 
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userEntity = new UserEntity();
		userEntity.setName("Bill");
		userEntity.setEmail("test1234@test.com");
		userEntity.setId(1L);
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
	}

	@Test
	final void testGetUser() {
 
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals("Bill", userDto.getName());

	}

	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userService.getUser("test@test.com");
				}
		);
	}
	
	@Test
	final void testCreateUser_CreateUserServiceException()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setName("Bill");
		userDto.setPassword("123456781");
		userDto.setEmail("t0est9099@test.com");
        userDto.setDate("2019-01-01");

 	
		assertThrows(UserServiceException.class,

				() -> {
					userService.createUser(userDto);
				}
		);
	}
	
	@Test
	final void testCreateUser()
	{
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generateId(anyInt())).thenReturn(userId);
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
 		
		UserDto userDto = new UserDto();
		userDto.setName("Bill");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");

		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getName(), storedUserDetails.getName());
		assertNotNull(storedUserDetails.getUserId());
		verify(bCryptPasswordEncoder, times(1)).encode("12345678");
		verify(userRepository,times(1)).save(any(UserEntity.class));
	}
	
	@Test
	final void testDeleteUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userService.deleteUser("test@test.com");
				}
		);
	}
	


}
