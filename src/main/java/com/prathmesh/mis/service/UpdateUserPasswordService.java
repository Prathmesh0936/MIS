package com.prathmesh.mis.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prathmesh.mis.dao.CheckOTPDao;
import com.prathmesh.mis.dao.SendEmailDao;
import com.prathmesh.mis.dao.UpdatePasswordDao;
import com.prathmesh.mis.entity.User;
import com.prathmesh.mis.repository.UserRepository;

@Service
public class UpdateUserPasswordService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	Random random = new Random();
	
	int OTP = 0;
	
	public User updatePassword(UpdatePasswordDao updatePasswordDao, String email) {
		
		User user = new User();
		
		user = this.userRepository.findByEmail(email).orElse(null);
		
		if(updatePasswordDao.getPassword_hash().equals(updatePasswordDao.getConfirmPassword())) {
			
			user.setPassword_hash(passwordEncoder.encode(updatePasswordDao.getPassword_hash()));
			
			this.userRepository.save(user);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password nad Confirm password does not Matched");
			
		}
		
		return user;
		
	}
	
	public String sendEmail(SendEmailDao sendEmailDao) {
		
		if(this.userRepository.findByEmail(sendEmailDao.getEmail()).isPresent()) {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			OTP = random.nextInt(10000);
			
			message.setFrom("prathmeshkshirsagar259@gmail.com");
			message.setTo(sendEmailDao.getEmail());
			message.setSubject("OTP For Verification");
			message.setText("Your OTP For Changing Your Account Password is " + OTP);
			
			mailSender.send(message);
			
			return "Email Send Successfully";
			
		} else {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Email is not Registered");
			
		}
		
	}
	
	public String checkOTP(CheckOTPDao checkOTPDao) {
		
		if(this.OTP == checkOTPDao.getOTP()) {
			
			return "OTP Matched";
			
		}else {
			
			return "OTP Not Matched";
			
		}
		
	}
}
