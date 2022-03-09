package com.camcar.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.model.Customer;
import com.camcar.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public boolean createCustomer(Customer customer) {
		boolean result = false;
		try {
			customerRepository.save(customer);
			result = true;
		}catch (IllegalArgumentException e) {		}
		return result;
	}

	@Override
	public boolean updateCustomer(int id, Customer customer) {
		boolean result = false;
		Customer customerData = customerRepository.findById(id);
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
	public Customer selectCustomerById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> selectAllCustomers() {
		return customerRepository.findAll();
	}

}