package com.camcar.customer.app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.camcar.customer.app.dto.DocumentRequest;
import com.camcar.customer.domain.service.dto.DocumentServiceData;

@Component
public class DocumentRequestConverter implements Converter<DocumentRequest, DocumentServiceData> {

	@Override
	public DocumentServiceData convert(DocumentRequest sourceDocument) {
		return DocumentServiceData.builder()
				.type(sourceDocument.getType())
				.value(sourceDocument.getValue())
				.idCustomer(sourceDocument.getIdCustomer()).build();
	}

}
