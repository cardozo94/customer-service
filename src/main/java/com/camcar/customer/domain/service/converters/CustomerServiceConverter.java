package com.camcar.customer.domain.service.converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.Document;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;
import com.camcar.customer.domain.service.dto.CustomerServiceDto.CustomerServiceDtoBuilder;

@Component
public class CustomerServiceConverter implements Converter<Customers, CustomerServiceDto> {

	@Override
	public CustomerServiceDto convert(Customers customerSource) {
//		if(customerSource!= null) {
		CustomerServiceDtoBuilder customerBuilder = CustomerServiceDto.builder()
					.id(customerSource.getId())
				.name(customerSource.getName())
				.address(customerSource.getAddress())
				.phoneNumber(customerSource.getPhoneNumber());
		List<Document> documents = customerSource.getDocument();
		if (documents!= null) {
			DocumentServiceConverter documentConverter = new DocumentServiceConverter();
			customerBuilder.documents(documents.stream().map(document -> documentConverter.convert(document)).toList());
		}
		return customerBuilder.build();
//		}
		
//		return null;
	}

}