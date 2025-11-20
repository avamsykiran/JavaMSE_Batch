package com.cts.profilesdemo.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleRunner implements CommandLineRunner {
	
	@Value("${spring.application.name}")
	private String appName;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(appName);
	}
	
	
}
