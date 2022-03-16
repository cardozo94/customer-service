package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.controller.dto.CustomerRequest;
import com.camcar.customer.service.dto.CustomerServiceData;

public class CustomerRequestConverter implements Converter<CustomerRequest, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(CustomerRequest customerSource) {
		CustomerServiceData customer = new CustomerServiceData();
		customer.setName(customerSource.getName());
		customer.setAddress(customerSource.getAddress());
		customer.setPhoneNumber(customerSource.getPhoneNumber());
		customer.setType(customerSource.getType());
		customer.setValue(customerSource.getValue());
		return customer;
	}

}
