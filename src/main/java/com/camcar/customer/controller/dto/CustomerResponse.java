package com.camcar.customer.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

	@Setter(value = AccessLevel.NONE)
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
}
