package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.controller.dto.CustomerResponse;
import com.camcar.customer.service.dto.CustomerServiceData;

@Component
public class CustomerResponseConverter implements Converter<CustomerServiceData, CustomerResponse> {

	@Override
	public CustomerResponse convert(CustomerServiceData customerSource) {
		CustomerResponse customer = null;
		if(customerSource != null) {
			customer = CustomerResponse.builder()
					.id(customerSource.getId())
					.name(customerSource.getName())
					.address(customerSource.getAddress())
					.phoneNumber(customerSource.getPhoneNumber())
					.type(customerSource.getType())
					.value(customerSource.getValue()).build();
		}
		return customer;
	}

}
