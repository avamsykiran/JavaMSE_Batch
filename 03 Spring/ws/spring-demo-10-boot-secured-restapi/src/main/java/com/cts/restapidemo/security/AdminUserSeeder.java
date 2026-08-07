package com.cts.restapidemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cts.restapidemo.entities.UserAccount;
import com.cts.restapidemo.repos.UserRepository;

@Component
public class AdminUserSeeder implements CommandLineRunner {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		if(!userRepo.existsByUsername("admin")) {
			userRepo.save(new UserAccount(null,"admin",encoder.encode("admin"),"ROLE_ADMIN"));
		}
	}
}
