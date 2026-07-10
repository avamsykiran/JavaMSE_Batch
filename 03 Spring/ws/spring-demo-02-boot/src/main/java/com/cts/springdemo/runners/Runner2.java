package com.cts.springdemo.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner2 implements CommandLineRunner {
	
	private Logger logger;
	
	public Runner2() {
		this.logger = LoggerFactory.getLogger(getClass());
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(this.getClass().getName() + " is executed");
	}

}
