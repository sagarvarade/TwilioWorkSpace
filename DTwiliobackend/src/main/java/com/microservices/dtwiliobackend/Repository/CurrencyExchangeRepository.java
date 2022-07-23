package com.microservices.dtwiliobackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.dtwiliobackend.Controllers.Beans.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer>{

	CurrencyExchange findByFromAndTo(String from,String to);
}
