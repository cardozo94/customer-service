package com.camcar.customer.app;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.camcar.customer.app.dto.DocumentRequest;
import com.camcar.customer.app.dto.DocumentResponse;
import com.camcar.customer.domain.service.DocumentServiceImpl;
import com.camcar.customer.domain.service.ServiceDefinition;
import com.camcar.customer.domain.service.dto.DocumentServiceData;

@RestController
@RequestMapping("customer/document")
public class DocumentController {

	@Autowired
	private ServiceDefinition<DocumentServiceData> documentService;
	@Autowired
	private ConversionService converter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DocumentResponse createDocument(@RequestBody DocumentRequest documentReq) {
		return converter.convert(documentService.create(converter.convert(documentReq, DocumentServiceData.class)),
				DocumentResponse.class);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDocument(@PathVariable("id") int id, @RequestBody DocumentRequest documentReq) {
		if (!documentService.update(id, converter.convert(documentReq, DocumentServiceData.class)))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not Found");
	}

	@GetMapping
	public List<DocumentResponse> getAllDocument() {
		return documentService.selectAll().stream().map(document -> converter.convert(document, DocumentResponse.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public DocumentResponse getDocumentById(@PathVariable("id") int id) {
		DocumentResponse customer = converter.convert(documentService.selectById(id), DocumentResponse.class);
		if (customer == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not Found");
		return customer;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletedocument(@PathVariable("id") int id) {
		if (!documentService.delete(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not Found");
	}

	@GetMapping("/customerId/{id}")
	public List<DocumentResponse> getDocumentsByCustomerId(@PathVariable("id") int customerId) {
		List<DocumentResponse> documents = ((DocumentServiceImpl) documentService).selectByCustomerId(customerId)
				.stream().map(document -> converter.convert(document, DocumentResponse.class)).toList();
		if (documents.size() == 0)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Documents not Found");
		return documents;
	}
}