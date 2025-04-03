package com.prathmesh.mis.dao;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class RegisterUserDao {
	
	@NotNull
	private String full_name;
	@NotNull
	private String email;
	@NotNull
	private String password_hash;
	private List<String> role;
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
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
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "RegisterUserDao [full_name=" + full_name + ", email=" + email + ", password_hash=" + password_hash
				+ ", role=" + role + "]";
	}
	
}
