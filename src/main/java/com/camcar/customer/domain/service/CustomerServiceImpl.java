package com.camcar.customer.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;
import com.camcar.customer.infrastructure.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	private ModelMapper mapper = new ModelMapper();

	@Override
	public boolean createCustomer(CustomerServiceDto customer) {
		boolean result = false;
		try {
			customerRepository.save(mapper.map(customer, Customers.class));
			result = true;
		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean updateCustomer(int id, CustomerServiceDto customer) {
		boolean result = false;
		Customers customerData = customerRepository.findById(id);
		if (customerData != null) {
			customerData.setName(customer.getName());
			customerData.setAddress(customer.getAddress());
			customerData.setPhoneNumber(customer.getPhoneNumber());
			System.out.println(customerData.getId());
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
		Customers customerRepo = customerRepository.findById(id);
		CustomerServiceDto customer;
		if(customerRepo != null) 
			customer = mapper.map(customerRepository.findById(id), CustomerServiceDto.class);
		else 
			customer = new CustomerServiceDto(0, "Not found", "-", "-");
		
		return customer;
	}

	@Override
	public List<CustomerServiceDto> selectAllCustomers() {
		return customerRepository.findAll().stream().map(customer -> mapper.map(customer, CustomerServiceDto.class))
				.collect(Collectors.toList());
	}

}
