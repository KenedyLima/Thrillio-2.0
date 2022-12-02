package com.thrillio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user1 = User.withUsername("user1").password(encoder().encode("user1Pass")).roles("USER").build();
		UserDetails user2 = User.withUsername("user2").password(encoder().encode("user2Pass")).roles("USER").build();
		UserDetails admin = User.withUsername("admin").password(encoder().encode("adminPass")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2, admin);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/anonymous*")
				.anonymous().antMatchers("/login*").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/signUp");

		return http.build();
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}