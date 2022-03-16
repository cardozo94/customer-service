package com.camcar.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServiceData {

	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private int idDocument;
	private String type;
	private String value;	

	public CustomerServiceData(int id, String name, String address, String phoneNumber){
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
}
