package com.cts.springaopdemo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cts.springaopdemo.aspect.PerformanceLoggingAspect;
import com.cts.springaopdemo.service.GreetService;

@Component
public class AppRunner implements CommandLineRunner {
	
	@Autowired
	private GreetService greetService;
	
	private static final Logger logger = LoggerFactory.getLogger(PerformanceLoggingAspect.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info(greetService.greetUser("Vamsy"));
	}

}
