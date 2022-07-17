package com.microservices.ctwilioui.Proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.ctwilioui.Controller.Beans.CurrencyConversion;



//@FeignClient(name="currency-exchange",url="localhost:8000")
@FeignClient(name="DTwiliobackend")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from,@PathVariable("to") String to);
	
}
