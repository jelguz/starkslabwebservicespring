package com.finastra.starkslab.webservice.model;

public class User extends Person {
	private String email;
	private String password;
	private boolean successfulLogin;
	
	public User() {
		super();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isSuccessfulLogin() {
		return successfulLogin;
	}
	public void setSuccessfulLogin(boolean successfulLogin) {
		this.successfulLogin = successfulLogin;
	}
}
