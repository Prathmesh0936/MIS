package com.prathmesh.mis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prathmesh.mis.dao.RegisterUserDao;
import com.prathmesh.mis.entity.User;
import com.prathmesh.mis.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(RegisterUserDao registerUserDao) {
		
		if(this.userRepository.findByEmail(registerUserDao.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already register with this email");
		}
		
		User user = new User();
		
		user.setFull_name(registerUserDao.getFull_name());
		user.setEmail(registerUserDao.getEmail());
		user.setPassword_hash(passwordEncoder.encode(registerUserDao.getPassword_hash()));
		user.setRole(registerUserDao.getRole());
		
		this.userRepository.save(user);
		
		return user;
		
	}
}
