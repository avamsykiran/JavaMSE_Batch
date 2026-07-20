package in.bta.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bta.auth.dto.RegistrationRequest;

@RestController
@RequestMapping("/register")
public class RegisterController {

	private final InMemoryUserDetailsManager userDetailsManager;
	private final PasswordEncoder passwordEncoder;

	public RegisterController(InMemoryUserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
		this.userDetailsManager = userDetailsManager;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping
	public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
		if (userDetailsManager.userExists(request.username())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		}

		UserDetails user = User.withUsername(request.username())
			.password(passwordEncoder.encode(request.password()))
			.roles("ACCOUNT_HOLDERS")
			.build();

		userDetailsManager.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Account-holder registered");
	}
}
