package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.controller.dto.CustomerResponse;
import com.camcar.customer.service.dto.CustomerServiceDto;

@Component
public class CustomerResponseConverter implements Converter<CustomerServiceDto, CustomerResponse> {

	@Override
	public CustomerResponse convert(CustomerServiceDto customerSource) {
		return new CustomerResponse(customerSource.getId(), customerSource.getName(), customerSource.getAddress(),
				customerSource.getPhoneNumber());
	}

}
