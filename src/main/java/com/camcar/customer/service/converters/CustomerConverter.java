package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.model.Customer;
import com.camcar.customer.service.dto.CustomerServiceData;

public class CustomerConverter implements Converter<CustomerServiceData, Customer> {

	@Override
	public Customer convert(CustomerServiceData customerSource) {
		Customer customer = new Customer();
		customer.setName(customerSource.getName());
		customer.setAddress(customerSource.getAddress());
		customer.setPhoneNumber(customerSource.getPhoneNumber());
		return customer;
	}

}
