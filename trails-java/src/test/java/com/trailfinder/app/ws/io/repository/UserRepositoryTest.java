package com.trailfinder.app.ws.io.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;
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
		
		if(!recordsCreated) createRecords();
	}

	
	@Test 
	final void testFindUsersByKeyword()
	{
		String keyword="yro";
		List<UserEntity> users = userRepository.findUsersByKeyword(keyword);
		assertNotNull(users);
		assertTrue(users.size() >= 2);
		
		UserEntity user = users.get(0);
		assertTrue( user.getName().contains(keyword) || 
					user.getEmail().contains(keyword) );
	}
	
	
	
	@Test 
	final void testFindUserEntityByUserId()
	{
		String userId = "1a2b3ccc";
		UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
		
		assertNotNull(userEntity);
		assertTrue(userEntity.getUserId().equals(userId));
	}
	
	
	private void createRecords()
	{
        Timestamp ts=new Timestamp(System.currentTimeMillis());  
		
		// Prepare User Entity
	     UserEntity userEntity = new UserEntity();
	     userEntity.setName("Byron Daniels");
	     userEntity.setUserId("1a2b3ccc");
	     userEntity.setEncryptedPassword("xaxazazx");
	     userEntity.setEmail("atesat696k9@test.com");
	     userEntity.setDate(new Date(ts.getTime()));
	     
	     
	     userRepository.save(userEntity);
	     
	     
			// Prepare User Entity
	     UserEntity userEntity2 = new UserEntity();
	     userEntity2.setName("Joe Daniels");
	     userEntity2.setUserId("1a2b3cddddd");
	     userEntity2.setEncryptedPassword("zxxazazax");
	     userEntity2.setEmail("ateast65o63@test.com");
	     userEntity2.setDate(new Date(ts.getTime()));
	     
	     userRepository.save(userEntity2);
	     
	     recordsCreated = true;
    
	}

}
