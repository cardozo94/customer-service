package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.controller.dto.DocumentRequest;
import com.camcar.customer.service.dto.DocumentServiceData;

public class DocumentRequestConverter implements Converter<DocumentRequest, DocumentServiceData> {

	@Override
	public DocumentServiceData convert(DocumentRequest sourceDocument) {
		DocumentServiceData document = new DocumentServiceData();
		document.setType(sourceDocument.getType());
		document.setValue(sourceDocument.getValue());
		document.setIdCustomer(sourceDocument.getIdCustomer());
		return document;
	}

}
