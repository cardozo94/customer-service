package com.camcar.customer.app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.app.dto.CustomerRequest;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;
import com.camcar.customer.domain.service.dto.CustomerServiceDto.CustomerServiceDtoBuilder;

@Component
public class CustomerRequestConverter implements Converter<CustomerRequest, CustomerServiceDto> {

	@Override
	public CustomerServiceDto convert(CustomerRequest customerSource) {
		CustomerServiceDtoBuilder customerBuilder = CustomerServiceDto.builder()
				.name(customerSource.getName()).address(customerSource.getAddress())
				.phoneNumber(customerSource.getPhoneNumber());

		if (customerSource.getDocuments() != null) {
			DocumentRequestConverter documentConverter = new DocumentRequestConverter();
			customerBuilder.documents(customerSource.getDocuments().stream()
					.map(document -> documentConverter.convert(document)).toList());
		}
		return customerBuilder.build();
	}

}
