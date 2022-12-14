
package com.thrillio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	public DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email, password, enabled from users where email = ?")
				.authoritiesByUsernameQuery("select email, authority from authorities where email = ?");
	}

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests(authz -> authz.antMatchers("/").permitAll()
//				.antMatchers("/bookmark-management/**").hasRole("USER").antMatchers("/auth/user").hasRole("USER")
//				.antMatchers("/auth/users").hasAnyRole("USER, ADMIN")
//				.antMatchers("/browse").hasRole("USER").antMatchers("/bookmarks").hasRole("USER")).formLogin().loginPage("/auth/login")
//				.loginProcessingUrl("/perform_login").defaultSuccessUrl("/bookmarks").failureUrl("auth/login")
//				.usernameParameter("email");
		http.csrf().disable();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/resources/**");
	}
}