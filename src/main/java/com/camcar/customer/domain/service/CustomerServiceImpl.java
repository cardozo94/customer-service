package com.camcar.customer.domain.service;

import java.util.List;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public boolean createCustomer(Customers customer) {
		boolean result = false;
		try {
			customerRepository.save(customer);
			result = true;
		}catch (IllegalArgumentException e) {		}
		return result;
	}

	@Override
	public boolean updateCustomer(int id, Customers customer) {
		boolean result = false;
		Customers customerData = customerRepository.findById(id).get();
		if(customerData != null) {
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
		}catch (Exception e) {	}
		return result;
	}

	@Override
	public Customers selectCustomerById(int id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public List<Customers> selectAllCustomers() {
		return customerRepository.findAll();
	}

}
