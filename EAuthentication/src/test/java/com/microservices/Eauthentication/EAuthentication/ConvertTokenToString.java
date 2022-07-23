package com.microservices.Eauthentication.EAuthentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ConvertTokenToString {
	private final String SECRET_KEY="ZmQ0ZGI5NjQ0MDQwY2I4MjMxY2Y3ZmI3MjdhN2ZmMjNhODViOTg1ZGE0NTBjMGM4NDA5NzYxMjdjOWMwYWRmZTBlZjlhNGY3ZTg4Y2U3YTE1ODVkZDU5Y2Y3OGYwZWE1NzUzNWQ2YjFjZDc0NGMxZWU2MmQ3MjY1NzJmNTE0MzI=";
	
	@Test
	public void testToken() {
		assertEquals(1, 1);
	}
	@Test
	public void decodeJWT() {
		String jwt="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYWdhciIsImlhdCI6MTY1ODA3ODYwMiwiZXhwIjoxNjU4MTY1MDAyfQ.FhORwTO7nJ9zuP8L1zqlr2p9y7V-zhY1MBNT7cxXHow0fI0-0KZkYw1fszUY8RGauvCkwm-Pg56kVH0j3LAFmg";
		Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	            .parseClaimsJws(jwt).getBody();
	    System.out.println("Claimms :: "+claims);
	}

}
