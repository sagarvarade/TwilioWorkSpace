package com.microservices.Eauthentication.security.controller;

public class VerfifyToken {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "VerfifyToken [token=" + token + "]";
	}
	
	
}
