package com.trailfinder.app.ws.shared;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;


public class UtilsTest {
	
	@Mock
	Utils utils;
	
	@Test
	final void testValidateEmail() {
		
		String testEmail0 ="joe@gmail.com";
		assertEquals(utils.validateEmail(testEmail0), true);
 
		String testEmail1 ="byron@hotmail.ca";
		assertEquals(utils.validateEmail(testEmail1), true);
		
		String testEmail2 ="byronhotmail.com";
		assertEquals(utils.validateEmail(testEmail2), false);
		
		String testEmail3 ="byron@hotmailcom";
		assertEquals(utils.validateEmail(testEmail3), false);
		
		String testEmail4 ="@hotmail.com";
		assertEquals(utils.validateEmail(testEmail4), false);
		
		String testEmail5 ="byron@";
		assertEquals(utils.validateEmail(testEmail5), false);
		
		String testEmail6 ="byron@x.c";
		assertEquals(utils.validateEmail(testEmail6), false);
		
	}

}
