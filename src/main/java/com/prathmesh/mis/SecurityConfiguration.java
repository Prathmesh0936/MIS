package com.prathmesh.mis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.prathmesh.mis.service.MyUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
	
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST,"api/auth/**").permitAll()
		.anyRequest().permitAll()
		.and()
		.httpBasic(Customizer.withDefaults())
		.authenticationProvider(daoauthenticationProvider());
		
		return http.build();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public DaoAuthenticationProvider daoauthenticationProvider () {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(myUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
		
	}
	
	@Bean
	public UserDetailsService users() {
		
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN" , "USER")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	

}
