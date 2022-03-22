package com.camcar.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Data
//@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerResponse {

	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private String type;
	private String value;
	
//	public CustomerResponse(int id, String name, String address, String phoneNumber){
//		this.id = id;
//		this.name = name;
//		this.address = address;
//		this.phoneNumber = phoneNumber;
//	}
}
