package com.microservices.currencyexchangeservice.Controllers.Beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	private Integer id;
	private String name;
	private String fname;
	private String address;
	
	
	public Student() {
		super();
	}
	public Student(Integer id, String name, String fname, String address) {
		super();
		this.id = id;
		this.name = name;
		this.fname = fname;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", fname=" + fname + ", address=" + address + "]";
	}
	
	
}
