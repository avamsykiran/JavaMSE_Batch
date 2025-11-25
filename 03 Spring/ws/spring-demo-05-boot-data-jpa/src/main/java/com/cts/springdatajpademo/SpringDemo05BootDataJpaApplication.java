package com.cts.springdatajpademo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDemo05BootDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo05BootDataJpaApplication.class, args);
	}

	@Bean
	Scanner kbin() {
		return new Scanner(System.in);
	}
}
