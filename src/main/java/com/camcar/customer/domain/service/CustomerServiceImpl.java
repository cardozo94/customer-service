package com.camcar.customer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.infrastructure.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customers selectCustomerById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customers> selectAllCustomers() {
		return customerRepository.findAll();
	}

}
