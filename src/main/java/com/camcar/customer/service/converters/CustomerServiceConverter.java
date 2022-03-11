package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.model.Customer;
import com.camcar.customer.service.dto.CustomerServiceDto;

public class CustomerServiceConverter implements Converter<Customer, CustomerServiceDto> {

	@Override
	public CustomerServiceDto convert(Customer customerSource) {
		return new CustomerServiceDto(customerSource.getId(), customerSource.getName(), customerSource.getAddress(),
				customerSource.getPhoneNumber());
	}

}
