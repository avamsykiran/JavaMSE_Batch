package com.cts.restapidemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.restapidemo.entities.UserAccount;
import com.cts.restapidemo.exceptions.InvalidRequestBodyException;
import com.cts.restapidemo.services.JwtService;
import com.cts.restapidemo.services.UserAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserAccountService userService;
			
	@Autowired
	private JwtService jwtService; // Custom service to generate tokens
	
	@Autowired
	private AuthenticationManager authManager;

    @PostMapping("/signup")
    public String signUp(@RequestBody @Valid UserAccount user,BindingResult bindingResult) throws InvalidRequestBodyException {
    	
    	if(bindingResult.hasErrors()) {
    		throw new InvalidRequestBodyException(bindingResult);
    	}
    	
    	userService.createUser(user);
       
        return "User registered successfully!";
    }

    public record LoginRequest(String username,String password) {} 
    
    @PostMapping("/signin")
    public String signIn(@RequestBody LoginRequest request) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        
        return jwtService.generateToken(auth.getName(), auth.getAuthorities());
    }
   

}