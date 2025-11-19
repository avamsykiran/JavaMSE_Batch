package com.cts.junitdemo.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cts.junitdemo.exceptions.InvalidUserNameException;
import com.cts.junitdemo.services.WelcomeServiceImpl;

public class TestWelcomeServiceImpl {

	private WelcomeServiceImpl welcomeServiceImpl;
	
	@BeforeEach
	void createWelcoemService() {
		this.welcomeServiceImpl = new WelcomeServiceImpl();
	}
	
	@AfterEach
	void destroyWelcoemService() {
		this.welcomeServiceImpl = null;
	}
	
	@Test
	@DisplayName("should return 'Hello Vamsy' when greet('vamsy') is called")
	void testGreetWhenAValidUserNAmeIsProvided() throws InvalidUserNameException {
		String testData = "Vamsy";
		String expected = "Hello Vamsy";
		String actual = this.welcomeServiceImpl.greet(testData);
		
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("should throw InvalidUSerNameException when greet(null) is called")
	void testGreetWhenANullUserNAmeIsProvided() {
		String testData = null;		
		
		assertThrows(InvalidUserNameException.class, () -> welcomeServiceImpl.greet(testData));
	}
}
