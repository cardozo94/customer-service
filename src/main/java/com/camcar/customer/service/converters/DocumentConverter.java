package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.repository.model.Document;
import com.camcar.customer.service.dto.DocumentServiceData;

@Component
public class DocumentConverter implements Converter<DocumentServiceData, Document> {

	@Override
	public Document convert(DocumentServiceData documentSource) {
		Document document = new Document();
		document.setType(documentSource.getType());
		document.setValue(documentSource.getValue());
		Customer customer = new Customer();
		customer.setId(documentSource.getIdCustomer());
		document.setIdCustomer(customer.getId());
		return document;
	}

}
