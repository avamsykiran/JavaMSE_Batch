package in.bta.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails admin = User.withUsername("admin")
			.password(passwordEncoder.encode("admin123"))
			.roles("ADMINS")
			.build();

		UserDetails accountHolder1 = User.withUsername("1")
			.password(passwordEncoder.encode("password"))
			.roles("ACCOUNT_HOLDERS")
			.build();

		UserDetails accountHolder2 = User.withUsername("2")
			.password(passwordEncoder.encode("password"))
			.roles("ACCOUNT_HOLDERS")
			.build();

		return new InMemoryUserDetailsManager(admin, accountHolder1, accountHolder2);
	}
}
