package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.controller.dto.CustomerResponse;
import com.camcar.customer.service.dto.CustomerServiceData;

@Component
public class CustomerResponseConverter implements Converter<CustomerServiceData, CustomerResponse> {

	@Override
	public CustomerResponse convert(CustomerServiceData customerSource) {

		if (customerSource != null) {
			DocumentResponseConverter documentConverter = new DocumentResponseConverter();
			CustomerResponse.CustomerResponseBuilder customerBuilder = CustomerResponse.builder()
					.id(customerSource.getId()).name(customerSource.getName()).address(customerSource.getAddress())
					.phoneNumber(customerSource.getPhoneNumber());
			if (customerSource.getDocuments() != null) {
				customerBuilder.documents(customerSource.getDocuments().stream()
						.map(document -> documentConverter.convert(document)).toList());
			}
			return customerBuilder.build();
		}
		return null;

	}

}
