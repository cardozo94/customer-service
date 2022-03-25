package com.camcar.customer.domain.service.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

@Component
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