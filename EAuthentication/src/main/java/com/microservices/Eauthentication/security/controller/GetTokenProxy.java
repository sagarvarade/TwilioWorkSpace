package com.microservices.Eauthentication.security.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="EAuthentication")
public interface GetTokenProxy {
	//@RequestMapping(method = RequestMethod.POST, value = "/gateway/test")
    //String getSessionId(@RequestHeader("X-Auth-Token") String token);
	
	//@PostMapping("/login")
	//TokenDetails updateRecord(@RequestBody);
	
	@RequestMapping("/login")
	TokenDetails message(@RequestHeader("Accept-Language") String language);
}
