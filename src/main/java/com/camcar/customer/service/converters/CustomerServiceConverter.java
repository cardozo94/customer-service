package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.service.dto.CustomerServiceData;

public class CustomerServiceConverter implements Converter<Customer, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(Customer customerSource) {
		return CustomerServiceData.builder()
				.id(customerSource.getId())
				.name(customerSource.getName())
				.address(customerSource.getAddress())
				.phoneNumber(customerSource.getPhoneNumber()).build();
	}
	

}
