package com.prathmesh.mis.dao;

public class SendEmailDao {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SendEmailDao [email=" + email + "]";
	}
	
}
