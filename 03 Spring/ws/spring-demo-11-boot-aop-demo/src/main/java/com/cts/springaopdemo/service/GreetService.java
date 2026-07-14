package com.cts.springaopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class GreetService {
	public String greetUser(String userName) {
		return "Hello " + userName;
	}
}
