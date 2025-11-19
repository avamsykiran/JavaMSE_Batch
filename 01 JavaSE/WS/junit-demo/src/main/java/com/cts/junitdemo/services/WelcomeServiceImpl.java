package com.cts.junitdemo.services;

import com.cts.junitdemo.exceptions.InvalidUserNameException;

public class WelcomeServiceImpl {

	public String greet(String userName) throws InvalidUserNameException {
		if(userName==null) {
			throw new InvalidUserNameException();
		}
		
		return "Hello " + userName;
	}
}
