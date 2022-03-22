package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.repository.model.Document;
import com.camcar.customer.service.dto.DocumentServiceData;

public class DocumentServiceConverter implements Converter<Document, DocumentServiceData> {

	@Override
	public DocumentServiceData convert(Document sourceDocument) {
		DocumentServiceData document;
		if(sourceDocument != null)
			document = DocumentServiceData.builder()
				.id(sourceDocument.getId())
				.type(sourceDocument.getType())
				.value(sourceDocument.getValue())
				.idCustomer(sourceDocument.getCustomer().getId()).build();
		else
			document = null;
		return document;
	}

}
