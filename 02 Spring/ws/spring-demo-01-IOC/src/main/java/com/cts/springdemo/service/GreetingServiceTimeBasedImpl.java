package com.cts.springdemo.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceTimeBasedImpl implements GreetingService {

	@Autowired
	private LocalTime timeNow;
	
	@Override
	public String grretUser(String userName) {
		String greeting = "";
		
		int h = timeNow.getHour();
		
		if(h>=3 && h<12) greeting="Good Morning";
		else if(h>=12 && h<15) greeting="Good Noon";
		else greeting="Good Evening";
				
		return greeting+" " +userName;
	}

}
