package com.cts.springdemo;

import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDemo02BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo02BootApplication.class, args);
	}

	@Bean
	Scanner scan() {
		return new Scanner(System.in);
	}
	
	@Bean
	LocalTime currentTime() {
		return LocalTime.now();
	}
}
