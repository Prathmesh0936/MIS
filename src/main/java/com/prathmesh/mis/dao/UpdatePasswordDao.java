package com.prathmesh.mis.dao;

public class UpdatePasswordDao {
	
	private String password_hash;
	
	private String confirmPassword;

	public String getPassword_hash() {
		return password_hash;
	}
	
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDao [password_hash=" + password_hash + ", confirmPassword=" + confirmPassword + "]";
	}
}
