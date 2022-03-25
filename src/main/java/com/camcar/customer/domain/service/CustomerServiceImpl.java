package com.camcar.customer.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;
import com.camcar.customer.domain.service.dto.DocumentServiceData;
import com.camcar.customer.infrastructure.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements ServiceDefinition<CustomerServiceDto> {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DocumentServiceImpl documentService;
	@Autowired
	private ConversionService converter;

	@Override
	public CustomerServiceDto create(CustomerServiceDto customer) {
		CustomerServiceDto result = null;
		try {
			result = converter.convert(customerRepository.save(converter.convert(customer, Customers.class)),
					CustomerServiceDto.class);
		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean update(int id, CustomerServiceDto customer) {
		boolean result = false;
		Customers customerData = customerRepository.findById(id);
		if (customerData != null) {
			customerData.setName(customer.getName());
			customerData.setAddress(customer.getAddress());
			customerData.setPhoneNumber(customer.getPhoneNumber());
			customerRepository.save(customerData);
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		List<DocumentServiceData> documents = documentService.selectByCustomerId(id);
		if (!documents.isEmpty()) {
			for (DocumentServiceData document : documents) {
				documentService.delete(document.getId());
			}
		}
		Customers customer = customerRepository.findById(id);
				if(customer!= null) {
					customerRepository.delete(customer);
					result = true;
				}
		return result;
	}

	@Override
	public CustomerServiceDto selectById(int id) {
		return converter.convert(customerRepository.findById(id), CustomerServiceDto.class);
	}

	@Override
	public List<CustomerServiceDto> selectAll() {
		return customerRepository.selectAllCustomers().stream()
				.map(customer -> converter.convert(customer, CustomerServiceDto.class)).collect(Collectors.toList());
	}

	public List<CustomerServiceDto> selectAllInfoFromAllCustomers() {
		return customerRepository.selectAllInfoForAllCustomers().stream()
				.map(customer -> converter.convert(customer, CustomerServiceDto.class)).toList();
	}

	public CustomerServiceDto selectAllInfoCustomer(int id) {
		return converter.convert(customerRepository.findByIdAllInfoCustomer(id), CustomerServiceDto.class);
	}

	public CustomerServiceDto insertFullInfoCustomer(CustomerServiceDto customer) {
		CustomerServiceDto customerSave = null;
		List<DocumentServiceData> documents = customer.getDocuments();
		if (documents != null) {
			customerSave = create(customer);
			if (customerSave != null) {
				List<DocumentServiceData> documentsSave = new ArrayList<>();
				for (Iterator<DocumentServiceData> iterator = documents.iterator(); iterator.hasNext();) {
					DocumentServiceData document = iterator.next();
					document.setIdCustomer(customerSave.getId());
					documentsSave.add(documentService.create(document));
				}
				customerSave.setDocuments(documentsSave);
			}
		}
		return customerSave;
	}

}
