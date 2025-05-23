package com.prathmesh.mis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prathmesh.mis.dao.LoginDao;
import com.prathmesh.mis.dao.RegisterUserDao;
import com.prathmesh.mis.repository.UserRepository;
import com.prathmesh.mis.service.MyUserDetailsService;
import com.prathmesh.mis.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "https://misapp.netlify.app"
	})

@Controller
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	 @Autowired
	    private MyUserDetailsService myUserDetailsService;

	    @GetMapping("/role")
	    public ResponseEntity<?> getUserRole(@RequestParam String email) {
	        com.prathmesh.mis.entity.User user = myUserDetailsService.getUserByEmail(email);
	        Map<String, Object> response = new HashMap<>();
	        response.put("roles", user.getRole());
	        response.put("email", user.getEmail());
	        response.put("id", user.getId());
	        return ResponseEntity.ok(response);
	    }
	

	    @GetMapping("/roles/{email}")
	    public ResponseEntity<?> getRoles(@PathVariable String email) {
	        return userRepository.findByEmail(email)
	                .map(user -> ResponseEntity.ok(user.getRole()))
	                .orElse(ResponseEntity.notFound().build());
	    }
	    
	
}
