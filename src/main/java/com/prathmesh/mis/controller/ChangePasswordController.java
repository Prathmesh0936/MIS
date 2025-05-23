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
@RequestMapping("/api/users/changepassword")
public class ChangePasswordController {
	
	@Autowired
	private UpdateUserPasswordService changeUserPasswordService;

	@PutMapping("")
	public ResponseEntity<?> changePassword(@RequestBody UpdatePasswordDao changePasswordDAO, HttpSession session){
		
		String email = (String) session.getAttribute("email");
		
		return ResponseEntity.ok(this.changeUserPasswordService.updatePassword(changePasswordDAO, email));
		
	}
	
	@PostMapping("/email")
	public ResponseEntity<?> send(@RequestBody SendEmailDao sendEmailDAO , HttpSession session){
		
		session.setAttribute("email", sendEmailDAO.getEmail());
		
		return ResponseEntity.ok(this.changeUserPasswordService.sendEmail(sendEmailDAO));
		
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> checkOTP(@RequestBody CheckOTPDao checkOtpDAO){
		
		return ResponseEntity.ok(this.changeUserPasswordService.checkOTP(checkOtpDAO));
		
	}
}