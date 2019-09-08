package com.trailfinder.app.ws.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilsTest {
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Autowired
	Utils utils;
	
	@Test
	final void testValidateEmail() {
		
		
		String testEmail0 ="joe@gmail.com";
		Boolean res =utils.validateEmail(testEmail0);
		
		
		assertEquals(res, true);
 
		String testEmail1 ="byron@hotmail.ca";
		assertEquals(utils.validateEmail(testEmail1), true);
		
		String testEmail2 ="byronhotmail.com";
		assertEquals(utils.validateEmail(testEmail2), false);
		
		String testEmail3 ="byron@hotma@ilcom";
		assertEquals(utils.validateEmail(testEmail3), false);
		
		String testEmail4 ="@hotmail.com";
		assertEquals(utils.validateEmail(testEmail4), false);
		
		String testEmail5 ="byron@";
		assertEquals(utils.validateEmail(testEmail5), false);
		
		String testEmail6 ="byron@x.c";
		assertEquals(utils.validateEmail(testEmail6), false);
		
	}
	
	@Test
	final void testGenerateUserId() {
		String userId = utils.generateId(30);
		String userId2 = utils.generateId(30);
		
		assertNotNull(userId);
		assertNotNull(userId2);
		
		assertTrue(userId.length() == 30);
		assertTrue( !userId.equalsIgnoreCase(userId2) );
		
	}

}
