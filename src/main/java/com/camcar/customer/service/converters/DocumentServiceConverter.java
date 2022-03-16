package com.camcar.customer.service.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.model.Document;
import com.camcar.customer.service.dto.DocumentServiceData;

public class DocumentServiceConverter implements Converter<Document, DocumentServiceData> {

	@Override
	public DocumentServiceData convert(Document sourceDocument) {
		DocumentServiceData document;
		if(sourceDocument != null)
			document = new DocumentServiceData(sourceDocument.getId(), sourceDocument.getType(), sourceDocument.getValue(),
					sourceDocument.getCustomer().getId());
		else
			document = null;
		return document;
	}

}
