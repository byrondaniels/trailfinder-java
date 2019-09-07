package com.trailfinder.app.ws.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.trailfinder.app.ws.io.entity.UserEntity;
import com.trailfinder.app.ws.io.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	static boolean recordsCreated = false;
	

	@BeforeEach
	void setUp() throws Exception {
		
		if(!recordsCreated) createRecrods();
	}

	
	@Test 
	final void testFindUsersByKeyword()
	{
		String keyword="erg";
		List<UserEntity> users = userRepository.findUsersByKeyword(keyword);
		assertNotNull(users);
		assertTrue(users.size() == 2);
		
		UserEntity user = users.get(0);
		assertTrue( user.getName().contains(keyword) || 
					user.getEmail().contains(keyword) );
	}
	
	
	
	@Test 
	final void testFindUserEntityByUserId()
	{
		String userId = "1a2b3c";
		UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
		
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId));
	}
	
	@Test
	final void testGetUserEntityFullNameById()
	{
		String userId = "1a2b3c";
		List<Object[]> records =  userRepository.getUserEntityNameById(userId);
		
        assertNotNull(records);
        assertTrue(records.size() == 1);
        
        Object[] userDetails = records.get(0);
      
        String firstName = String.valueOf(userDetails[0]);
        String lastName = String.valueOf(userDetails[1]);

        assertNotNull(firstName);
        assertNotNull(lastName);
	}
	
	
	private void createRecrods()
	{
		// Prepare User Entity
	     UserEntity userEntity = new UserEntity();
	     userEntity.setName("Byron Daniels");
	     userEntity.setUserId("1a2b3c");
	     userEntity.setEncryptedPassword("xxx");
	     userEntity.setEmail("test@test.com");
	     
	     
	     userRepository.save(userEntity);
	     
	     
	     
	     
			// Prepare User Entity
	     UserEntity userEntity2 = new UserEntity();
	     userEntity2.setName("Joe Daniels");
	     userEntity2.setUserId("1a2b3cddddd");
	     userEntity2.setEncryptedPassword("xxx");
	     userEntity2.setEmail("test@test.com");
	     
	     
	     userRepository.save(userEntity2);
	     
	     recordsCreated = true;
    
	}

}
