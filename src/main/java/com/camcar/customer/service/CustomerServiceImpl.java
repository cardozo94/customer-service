package com.camcar.customer.service;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.model.Customer;
import com.camcar.customer.repository.CustomerRepository;
import com.camcar.customer.service.converters.CustomerConverter;
import com.camcar.customer.service.converters.CustomerServiceConverter;
import com.camcar.customer.service.converters.DocumentToCustomerConverter;
import com.camcar.customer.service.dto.CustomerServiceData;

@Service
public class CustomerServiceImpl implements ServiceDefinition<CustomerServiceData> {

	@Autowired
	private CustomerRepository customerRepository;
//	private ModelMapper mapper = new ModelMapper();
	private CustomerServiceConverter converterToCustomerService = new CustomerServiceConverter();
	private CustomerConverter conterterToCustomer = new CustomerConverter();
	private DocumentToCustomerConverter converterFromDocument = new DocumentToCustomerConverter();

	@Override
	public boolean create(CustomerServiceData customer) {
		boolean result = false;
		try {
			customerRepository.insertCustomer(conterterToCustomer.convert(customer));
			result = true;
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
			customerRepository.updateCustomer(customerData);
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		try {
			customerRepository.deleteCustomer(id);
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
			customer = converterToCustomerService.convert(customerRepo);
		else
			customer = new CustomerServiceData(0, "Not found", "-", "-");
		return customer;
	}

	@Override
	public List<CustomerServiceData> selectAll() {
		return customerRepository.selectAllCustomers().stream()
				.map(customer -> converterToCustomerService.convert(customer)).collect(Collectors.toList());
	}

	public List<CustomerServiceData> selectAllInfoFromAllCustomers() {
		return customerRepository.selectAllInfoForAllCustomers().stream()
				.map(customer -> converterFromDocument.convert(customer)).toList();
	}

	public CustomerServiceData selectAllInfoCustomer(int id) {
		return converterFromDocument.convert(customerRepository.findByIdAllInfoCustomer(id));
	}

}