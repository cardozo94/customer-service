package com.camcar.customer.domain.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.repository.CustomerRepository;
import com.camcar.customer.domain.service.converters.CustomerConverter;
import com.camcar.customer.domain.service.converters.CustomerServiceConverter;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
//	private ModelMapper mapper = new ModelMapper();
	private CustomerServiceConverter converterToCustomerService;
	private CustomerConverter conterterToCustomer;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
//		mapper = new ModelMapper();
		converterToCustomerService = new CustomerServiceConverter();
		conterterToCustomer = new CustomerConverter();
	}

	@Override
	public boolean createCustomer(CustomerServiceDto customer) {
		boolean result = false;
		try {
			customerRepository.save(conterterToCustomer.convert(customer));
			result = true;
		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean updateCustomer(int id, CustomerServiceDto customer) {
		boolean result = false;
		Optional<Customers> customerRepo = customerRepository.findById(id);
		if (!customerRepo.isEmpty()) {
			Customers customerData = customerRepo.get();
			customerData.setName(customer.getName());
			customerData.setAddress(customer.getAddress());
			customerData.setPhoneNumber(customer.getPhoneNumber());
			customerRepository.save(customerData);
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteCustomer(int id) {
		boolean result = false;
		try {
			customerRepository.deleteById(id);
			result = true;
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public CustomerServiceDto selectCustomerById(int id) {
		CustomerServiceDto customer;
		try {
			customer = converterToCustomerService.convert(customerRepository.findById(id).get());
		}catch (NoSuchElementException e) {
			customer = new CustomerServiceDto(0, "Not Found", "-", "-");
		}
		return customer; 
	}

	@Override
	public List<CustomerServiceDto> selectAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> converterToCustomerService.convert(customer))
				.collect(Collectors.toList());
	}

}
