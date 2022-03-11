package com.camcar.customer.domain.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;


public class CustomerConverter implements Converter<CustomerServiceDto, Customers> {

	@Override
	public Customers convert(CustomerServiceDto customerSource) {
		Customers customer = new Customers();
		customer.setName(customerSource.getName());
		customer.setAddress(customerSource.getAddress());
		customer.setPhoneNumber(customerSource.getPhoneNumber());
		return customer;
	}

}