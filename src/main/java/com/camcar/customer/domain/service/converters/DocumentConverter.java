package com.camcar.customer.domain.service.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.Document;
import com.camcar.customer.domain.service.dto.DocumentServiceData;

@Component
public class DocumentConverter implements Converter<DocumentServiceData, Document> {

	@Override
	public Document convert(DocumentServiceData documentSource) {
		Document document = new Document();
		document.setType(documentSource.getType());
		document.setValue(documentSource.getValue());
		Customers customer = new Customers();
		customer.setId(documentSource.getIdCustomer());
		document.setIdCustomer(customer.getId());
		return document;
	}

}