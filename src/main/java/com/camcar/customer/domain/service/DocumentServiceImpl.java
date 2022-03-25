package com.camcar.customer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.camcar.customer.infrastructure.repositories.DocumentRepository;
import com.camcar.customer.domain.Document;
import com.camcar.customer.domain.service.dto.DocumentServiceData;

@Service
public class DocumentServiceImpl implements ServiceDefinition<DocumentServiceData> {

	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ConversionService converter;

	@Override
	public DocumentServiceData create(DocumentServiceData document) {
		DocumentServiceData result = null;
		try {
			result = converter.convert(documentRepository.save(converter.convert(document, Document.class)),
					DocumentServiceData.class);
		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean update(int id, DocumentServiceData document) {
		boolean result = false;
		Document documentRepo = documentRepository.getById(id);
		if (documentRepo != null) {
			documentRepo.setType(document.getType());
			documentRepo.setValue(document.getValue());
			documentRepository.save(documentRepo);
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			documentRepository.deleteById(id);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DocumentServiceData selectById(int id) {
		return converter.convert(documentRepository.findById(id), DocumentServiceData.class);

	}

	@Override
	public List<DocumentServiceData> selectAll() {
		return documentRepository.findAll().stream()
				.map(document -> converter.convert(document, DocumentServiceData.class)).toList();
	}

	public List<DocumentServiceData> selectByCustomerId(int customerId) {
		return documentRepository.findByCustomerId(customerId).stream()
				.map(document -> converter.convert(document, DocumentServiceData.class)).toList();

	}

}