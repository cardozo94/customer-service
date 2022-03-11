package com.camcar.customer.app.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.app.dto.CustomerRequest;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

public class CustomerRequestConverter implements Converter<CustomerRequest, CustomerServiceDto> {

	@Override
	public CustomerServiceDto convert(CustomerRequest customerSource) {
		CustomerServiceDto customer = new CustomerServiceDto();
		customer.setName(customerSource.getName());
		customer.setAddress(customerSource.getAddress());
		customer.setPhoneNumber(customerSource.getPhoneNumber());
		return customer;
	}

}
