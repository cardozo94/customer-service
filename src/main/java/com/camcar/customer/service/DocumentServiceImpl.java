package com.camcar.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.repository.DocumentRepository;
import com.camcar.customer.repository.model.Document;
import com.camcar.customer.service.converters.DocumentConverter;
import com.camcar.customer.service.converters.DocumentServiceConverter;
import com.camcar.customer.service.dto.DocumentServiceData;

@Service
public class DocumentServiceImpl implements ServiceDefinition<DocumentServiceData> {

	@Autowired
	private DocumentRepository documentRepository;
	private DocumentConverter converterToDocument = new DocumentConverter();
	private DocumentServiceConverter converterToDocumentService = new DocumentServiceConverter();

	@Override
	public DocumentServiceData create(DocumentServiceData document) {
		DocumentServiceData result = null;
		try {
			result = converterToDocumentService.convert(documentRepository.save(converterToDocument.convert(document)));
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
		return converterToDocumentService.convert(documentRepository.findById(id));

	}

	@Override
	public List<DocumentServiceData> selectAll() {
		return documentRepository.findAll().stream().map(document -> converterToDocumentService.convert(document))
				.toList();
	}

	public List<DocumentServiceData> selectByCustomerId(int customerId) {
		return documentRepository.findByCustomerId(customerId).stream()
				.map(document -> converterToDocumentService.convert(document)).toList();

	}

}
