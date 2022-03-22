package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.repository.dto.CustomerDocumentData;
import com.camcar.customer.service.dto.CustomerServiceData;

public class DocumentToCustomerConverter implements Converter<CustomerDocumentData, CustomerServiceData> {

	@Override
	public CustomerServiceData convert(CustomerDocumentData sourceDocument) {
		CustomerServiceData customer;
		if (sourceDocument != null)
			if (sourceDocument.getDocument() != null)
				customer = CustomerServiceData.builder()
					.id(sourceDocument.getCustomer().getId())
					.name(sourceDocument.getCustomer().getName())
					.address(sourceDocument.getCustomer().getAddress())
					.phoneNumber(sourceDocument.getCustomer().getPhoneNumber())
					.idDocument(sourceDocument.getDocument().getId())
					.type(sourceDocument.getDocument().getType())
					.value(sourceDocument.getDocument().getValue()).build();
			else
				customer = CustomerServiceData.builder()
					.id(sourceDocument.getCustomer().getId())
					.name(sourceDocument.getCustomer().getName())
					.address(sourceDocument.getCustomer().getAddress())
					.phoneNumber(sourceDocument.getCustomer().getPhoneNumber()).build();
		else {
			customer = null;
		}
		return customer;
	}

}
