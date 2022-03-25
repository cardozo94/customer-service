package com.camcar.customer.service.converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.repository.model.Document;
import com.camcar.customer.service.dto.CustomerServiceData;

@Component
public class CustomerServiceConverter implements Converter<Customer, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(Customer customerSource) {
//		if(customerSource!= null) {
			CustomerServiceData.CustomerServiceDataBuilder customerBuilder = CustomerServiceData.builder()
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
