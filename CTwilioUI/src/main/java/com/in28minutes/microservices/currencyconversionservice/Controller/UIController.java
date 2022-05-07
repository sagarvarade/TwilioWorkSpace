package com.in28minutes.microservices.currencyconversionservice.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

	@GetMapping(path = {"/","/login"})
	public String getLoginPage() {
		System.out.println("Login path.........");
		return "login";
	}
	@GetMapping(path = {"/index"})
	public String getindexPage() {
		System.out.println("index path.........");
		return "index";
	}
}
