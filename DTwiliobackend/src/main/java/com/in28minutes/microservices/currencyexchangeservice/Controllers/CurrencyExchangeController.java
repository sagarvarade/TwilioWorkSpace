package com.in28minutes.microservices.currencyexchangeservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currencyexchangeservice.Controllers.Beans.CurrencyExchange;
import com.in28minutes.microservices.currencyexchangeservice.Repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;

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
}
