package com.cts.restapidemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.restapidemo.entities.UserAccount;
import com.cts.restapidemo.repos.UserRepository;

/*
 * When we define a UserDetailsService bean, 
 * Spring Boot's default configuration automatically detects it and 
 * "wires" it into the AuthenticationManager
 * */
@Service
public class UserDetailsServiceAndUserAccountServiceImpl implements UserDetailsService,UserAccountService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceAndUserAccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().split(",")) // Loads "ROLE_USER", etc.
                .build();
    }
    
    public UserAccount createUser(UserAccount user) {
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
         if(user.getRoles()==null) {
         	user.setRoles("ROLE_USER"); // Default role
         }
         return userRepository.save(user);
    }
}