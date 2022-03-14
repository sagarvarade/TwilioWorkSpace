package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CTwilioUI {

	public static void main(String[] args) {
		System.out.println(" app Start ");
		SpringApplication.run(CTwilioUI.class, args);
	}
	

}
