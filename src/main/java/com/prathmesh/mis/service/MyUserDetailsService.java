package com.prathmesh.mis.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prathmesh.mis.entity.User;
import com.prathmesh.mis.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository UserRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return this.UserRepository.findByEmail(username)
				.map( user -> {
					return new User(
							user.getEmail(),
							user.getPassword_hash(),
							user.getRole().stream()
							.map( role -> new SimpleGrantedAuthority(role))
							.collect(Collectors.toList())
									);
                      }).orElseThrow(
						
						() -> {
							throw new UsernameNotFoundException("User with this email not Found!!!");
						}
						
						);
	}

}
