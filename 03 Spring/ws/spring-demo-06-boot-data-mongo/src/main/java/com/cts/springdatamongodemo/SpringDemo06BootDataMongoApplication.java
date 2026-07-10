package com.cts.springdatamongodemo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDemo06BootDataMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo06BootDataMongoApplication.class, args);
	}

	
	@Bean
	Scanner kbin() {
		return new Scanner(System.in);
	}
}
