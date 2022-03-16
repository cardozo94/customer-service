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
		if (customerSource != null)
			customer = new CustomerResponse(customerSource.getId(), customerSource.getName(),
					customerSource.getAddress(), customerSource.getPhoneNumber(), customerSource.getType(),
					customerSource.getValue());
		return customer;
	}

}
