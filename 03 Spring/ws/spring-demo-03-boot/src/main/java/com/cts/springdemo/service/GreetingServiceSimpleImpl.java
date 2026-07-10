package com.cts.springdemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceSimpleImpl implements GreetingService {

	@Value("${greeting:Hello}")
	private String greeting;
	
	@Override
	public String grretUser(String userName) {
		return greeting+" " +userName;
	}

}
