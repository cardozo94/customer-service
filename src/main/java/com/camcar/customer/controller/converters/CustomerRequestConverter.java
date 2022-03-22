package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.controller.dto.CustomerRequest;
import com.camcar.customer.service.dto.CustomerServiceData;

//@Component --> para el converterService
public class CustomerRequestConverter implements Converter<CustomerRequest, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(CustomerRequest customerSource) {
		return CustomerServiceData.builder()
				.name(customerSource.getName())
				.address(customerSource.getAddress())
				.phoneNumber(customerSource.getPhoneNumber())
				.type(customerSource.getType())
				.value(customerSource.getValue()).build();
	}

}
