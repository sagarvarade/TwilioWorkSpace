package com.microservices.dtwiliobackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dtwiliobackend.Controllers.Beans.CurrencyExchange;
import com.microservices.dtwiliobackend.Controllers.Beans.Student;
import com.microservices.dtwiliobackend.Controllers.Beans.dockerRepository;
import com.microservices.dtwiliobackend.Repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private dockerRepository repo;
	
	@GetMapping("/currency-exchange/from/{from}/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to) {
		///http://localhost:8000/currency-exchange/from/USD/INR
		//CurrencyExchange currencyExchange = new CurrencyExchange(1,"USD","INR",BigDecimal.valueOf(50));
		//currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		if(currencyExchange==null) {
			throw new RuntimeException("Unable to find data");
		}
	
		return currencyExchange;
	}
	
	
	
	@GetMapping("/getstudent/{id}")
	public Student getSaveStudent(@PathVariable("id") Integer id) {
		Student std=repo.getById(id);
		System.out.println("Student get from to db "+std);
		return new Student(std.getId(),std.getName(),std.getFname(),std.getAddress());
	}
	
	@PostMapping("savestudent")
	public Student SaveStudent(@RequestBody Student std) {
		System.out.println("  "+std);
		try {
			Student act=repo.save(std);
			System.out.println("Student saved to db");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Student(std.getId(),std.getName(),std.getFname(),std.getAddress());
	}
}
