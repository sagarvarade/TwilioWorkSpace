package com.Twilio.EAuthApplication.tenant.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
    private String username;

	@NotBlank
	private String password;

	
	@NotBlank
	private String tenant;
	
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

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
}
