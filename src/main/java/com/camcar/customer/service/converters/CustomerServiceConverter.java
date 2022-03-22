package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.service.dto.CustomerServiceData;

public class CustomerServiceConverter implements Converter<Customer, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(Customer customerSource) {
		CustomerServiceData customer = new CustomerServiceData();
		customer.setId(customerSource.getId());
		customer.setName(customerSource.getName());
		customer.setAddress(customerSource.getAddress());
		customer.setPhoneNumber(customerSource.getPhoneNumber());
		return customer;
	}
	

}
