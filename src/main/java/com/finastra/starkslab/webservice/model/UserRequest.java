package com.finastra.starkslab.webservice.model;

public class UserRequest {
	private String id;
	private String password;
	
	public UserRequest() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
