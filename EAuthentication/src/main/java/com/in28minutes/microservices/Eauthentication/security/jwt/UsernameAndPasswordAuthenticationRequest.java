package com.in28minutes.microservices.Eauthentication.security.jwt;
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
    }

    public UsernameAndPasswordAuthenticationRequest(String username, String password) {
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
}
