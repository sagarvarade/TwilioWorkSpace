package com.in28minutes.microservices.Eauthentication.security.controller;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="EAuthentication")
public interface GetTokenProxy {
	//@RequestMapping(method = RequestMethod.POST, value = "/gateway/test")
    //String getSessionId(@RequestHeader("X-Auth-Token") String token);
	
	//@PostMapping("/login")
	//TokenDetails updateRecord(@RequestBody);
	
	@RequestMapping("/login")
	TokenDetails message(@RequestHeader("Accept-Language") String language);
}
