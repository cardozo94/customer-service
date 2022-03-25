package com.camcar.customer.app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.app.dto.DocumentResponse;
import com.camcar.customer.domain.service.dto.DocumentServiceData;

@Component
public class DocumentResponseConverter implements Converter<DocumentServiceData, DocumentResponse> {

	@Override
	public DocumentResponse convert(DocumentServiceData sourceDocument) {
		DocumentResponse document;
		if (sourceDocument != null)
			document = DocumentResponse.builder()
				.id(sourceDocument.getId())
				.type(sourceDocument.getType())
				.value(sourceDocument.getValue())
//				.idCustomer(sourceDocument.getIdCustomer())
				.build();
		else
			document = null;
		return document;
	}

}
