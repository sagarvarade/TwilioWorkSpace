package com.microservices.Eauthentication.security.controller;

public class TokenGeneratedBean {
	private String username;
	private String password;
	private String token;
	private int expiresIn;
	
	public TokenGeneratedBean() {
		super();
	}
	public TokenGeneratedBean(String username, String password, String token,int expiresIn) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
		this.expiresIn = expiresIn;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "TokenGeneratedBean [username=" + username + ", password=" + password + ", token=" + token + "]";
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
