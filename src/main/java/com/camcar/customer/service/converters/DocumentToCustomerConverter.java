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
				customer = new CustomerServiceData(sourceDocument.getCustomer().getId(),
						sourceDocument.getCustomer().getName(), sourceDocument.getCustomer().getAddress(),
						sourceDocument.getCustomer().getPhoneNumber(), sourceDocument.getDocument().getId(),
						sourceDocument.getDocument().getType(), sourceDocument.getDocument().getValue());
			else
				customer = new CustomerServiceData(sourceDocument.getCustomer().getId(),
						sourceDocument.getCustomer().getName(), sourceDocument.getCustomer().getAddress(),
						sourceDocument.getCustomer().getPhoneNumber());
		else {
			customer = null;
		}
		return customer;
	}

}
