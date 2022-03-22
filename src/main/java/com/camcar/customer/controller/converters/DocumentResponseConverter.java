package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.controller.dto.DocumentResponse;
import com.camcar.customer.service.dto.DocumentServiceData;

public class DocumentResponseConverter implements Converter<DocumentServiceData, DocumentResponse> {

	@Override
	public DocumentResponse convert(DocumentServiceData sourceDocument) {
		DocumentResponse document;
		if (sourceDocument != null)
			document = DocumentResponse.builder()
				.id(sourceDocument.getId())
				.type(sourceDocument.getType())
				.value(sourceDocument.getValue())
				.idCustomer(sourceDocument.getIdCustomer()).build();
		else
			document = null;
		return document;
	}

}
