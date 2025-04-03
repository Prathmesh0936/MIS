package com.prathmesh.mis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prathmesh.mis.dao.CheckOTPDao;
import com.prathmesh.mis.dao.SendEmailDao;
import com.prathmesh.mis.dao.UpdatePasswordDao;
import com.prathmesh.mis.service.UpdateUserPasswordService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("api/user/updatePassword")
public class UpdateUserController {
	
	@Autowired
	private UpdateUserPasswordService updateUserPasswordService;
	
	@PutMapping("")
	public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDao updatePasswordDao, HttpSession session){
		
		String email = (String) session.getAttribute("email");
		
		return ResponseEntity.ok(this.updateUserPasswordService.updatePassword(updatePasswordDao, email));
		
	}
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendEmailDao sendEmailDao , HttpSession session){
		
		session.setAttribute("email", sendEmailDao.getEmail());
		
		return ResponseEntity.ok(this.updateUserPasswordService.sendEmail(sendEmailDao));
		
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> checkOTP(@RequestBody CheckOTPDao checkOTPDao){
		
		return ResponseEntity.ok(this.updateUserPasswordService.checkOTP(checkOTPDao));
		
	}

}
