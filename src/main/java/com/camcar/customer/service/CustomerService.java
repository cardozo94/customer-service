package com.camcar.customer.service;

import java.util.List;

import com.camcar.customer.model.Customer;

public interface CustomerService {

	boolean createCustomer(Customer customer);
	boolean updateCustomer(int d, Customer customer);
	boolean deleteCustomer(int id);
	Customer selectCustomerById(int id);
	List<Customer> selectAllCustomers();
	
}
