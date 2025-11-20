package com.cts.springdemo.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cts.springdemo.service.GreetingService;
import com.cts.springdemo.util.Counter;

@Component
public class SimpleUI {
	
	@Autowired
	private Counter c1;
	
	@Autowired
	private Counter c2;
	
	@Autowired
	private Scanner kbin;
	
	@Autowired
	@Qualifier("greetingServiceSimpleImpl")
	private GreetingService gs1;

	@Autowired
	@Qualifier("greetingServiceTimeBasedImpl")
	private GreetingService gs2;
	
	
	public void run() {
		System.out.println("Hello From Spring");
		
		System.out.println(c1.next());
		System.out.println(c1.next());
		System.out.println(c1.next());
		
		System.out.println(c2.next());
		System.out.println(c2.next());
		System.out.println(c2.next());
		
		System.out.println("UserName? ");
		String userName = kbin.next();
		
		System.out.println(gs1.grretUser(userName));
		
		System.out.println(gs2.grretUser(userName));
	}
}
