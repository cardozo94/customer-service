package com.camcar.customer.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.camcar.customer.repository.CustomerRepository;
import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.service.dto.CustomerServiceData;
import com.camcar.customer.service.dto.DocumentServiceData;

@Service
public class CustomerServiceImpl implements ServiceDefinition<CustomerServiceData> {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DocumentServiceImpl documentService;
//	private ModelMapper mapper = new ModelMapper();
	@Autowired
	private ConversionService converter;
//	private CustomerServiceConverter converterToCustomerService = new CustomerServiceConverter();
//	private CustomerConverter conterterToCustomer = new CustomerConverter();

	@Override
	public CustomerServiceData create(CustomerServiceData customer) {
		CustomerServiceData result = null;
		try {
			result = converter.convert(customerRepository.save(converter.convert(customer, Customer.class)),
					CustomerServiceData.class);

		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean update(int id, CustomerServiceData customer) {
		boolean result = false;
		Customer customerData = customerRepository.findById(id);
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
		try {
			for (DocumentServiceData document : documentService.selectByCustomerId(id)) {
				documentService.delete(document.getId());
			}
			customerRepository.delete(customerRepository.findById(id));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CustomerServiceData selectById(int id) {
		Customer customerRepo = customerRepository.findById(id);
		CustomerServiceData customer;
		if (customerRepo != null)
			customer = converter.convert(customerRepo, CustomerServiceData.class);
		else
			customer = CustomerServiceData.builder().id(0).name("Not Found").address("-").phoneNumber("-").build();
		return customer;
	}

	@Override
	public List<CustomerServiceData> selectAll() {
		return customerRepository.selectAllCustomers().stream()
				.map(customer -> converter.convert(customer, CustomerServiceData.class)).collect(Collectors.toList());
	}

	public List<CustomerServiceData> selectAllInfoFromAllCustomers() {
		return customerRepository.selectAllInfoForAllCustomers().stream()
				.map(customer -> converter.convert(customer, CustomerServiceData.class)).toList();
	}

	public CustomerServiceData selectAllInfoCustomer(int id) {
		return converter.convert(customerRepository.findByIdAllInfoCustomer(id), CustomerServiceData.class);
	}

	public CustomerServiceData insertFullInfoCustomer(CustomerServiceData customer) {
		CustomerServiceData customerSave = null;
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