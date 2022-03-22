package com.camcar.customer.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Setter(value = AccessLevel.NONE)
	private int id;
	private String name;
	private String address;
	private String phoneNumber;

}
