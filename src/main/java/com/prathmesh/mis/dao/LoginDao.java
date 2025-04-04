package com.prathmesh.mis.dao;

import jakarta.validation.constraints.NotBlank;

public class LoginDao {
	
	@NotBlank
	private String email;
	@NotBlank
	private String password_hash;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	
	@Override
	public String toString() {
		return "LoginDao [email=" + email + ", password_hash=" + password_hash + "]";
	}
	
}
