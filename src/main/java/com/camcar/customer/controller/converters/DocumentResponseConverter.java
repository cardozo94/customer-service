package com.camcar.customer.controller.converters;

import org.springframework.core.convert.converter.Converter;

import com.camcar.customer.controller.dto.DocumentResponse;
import com.camcar.customer.service.dto.DocumentServiceData;

public class DocumentResponseConverter implements Converter<DocumentServiceData, DocumentResponse> {

	@Override
	public DocumentResponse convert(DocumentServiceData sourceDocument) {
		DocumentResponse document;
		if (sourceDocument != null)
			document = new DocumentResponse(sourceDocument.getId(), sourceDocument.getType(), sourceDocument.getValue(),
					sourceDocument.getIdCustomer());
		else
			document = null;
		return document;
	}

}
