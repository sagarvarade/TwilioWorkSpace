package com.in28minutes.microservices.Eauthentication.security.controller;

public class TokenDetails {
	
	private String username;
	private String password;
	
	public TokenDetails() {
		super();
	}
	public TokenDetails(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "TokenDetails [username=" + username + ", password=" + password + ", tokenGenerated="
				+ "]";
	}
	
}
