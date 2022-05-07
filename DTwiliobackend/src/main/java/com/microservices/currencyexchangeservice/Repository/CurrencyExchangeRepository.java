package com.microservices.currencyexchangeservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.currencyexchangeservice.Controllers.Beans.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer>{

	CurrencyExchange findByFromAndTo(String from,String to);
}
