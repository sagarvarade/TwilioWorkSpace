package com.microservices.ctwilioui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ctwilioui {

	public static void main(String[] args) {
		System.out.println(" app Start ");
		SpringApplication.run(ctwilioui.class, args);
	}
	

}
