package com.camcar.customer.domain.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

public class CustomerServiceConverter implements Converter<Customers, CustomerServiceDto> {

	@Override
	public CustomerServiceDto convert(Customers customerSource) {
		return new CustomerServiceDto(customerSource.getId(), customerSource.getName(), customerSource.getAddress(),
				customerSource.getPhoneNumber());
	}

}