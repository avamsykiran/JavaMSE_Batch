package com.cts.springdemo;

import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.cts.springdemo")
@PropertySource("classpath:application.properties")
public class BeanConfig {

	@Bean
	Scanner scan() {
		return new Scanner(System.in);
	}
	
	@Bean
	LocalTime currentTime() {
		return LocalTime.now();
	}
}
