package com.in28minutes.microservices.currencyexchangeservice.Controllers.Beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dockerRepository extends JpaRepository<Student, Integer>{

}
