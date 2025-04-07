package com.prathmesh.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prathmesh.mis.dao.LoginDao;
import com.prathmesh.mis.dao.RegisterUserDao;
import com.prathmesh.mis.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register (@RequestBody @Valid RegisterUserDao registerUserDao){
		return ResponseEntity.ok(this.userService.registerUser(registerUserDao));
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody @Valid LoginDao loginDao){
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDao.getEmail(), loginDao.getPassword_hash()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return ResponseEntity.ok("User Logged In Successfully!!!");
	}
	
}
