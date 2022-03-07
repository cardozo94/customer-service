package com.camcar.customer.domain.service;

import java.util.List;

import com.camcar.customer.domain.Customers;

public interface CustomerService {
	
	boolean createCustomer(Customers customer);
	boolean updateCustomer();
	boolean deleteCustomer(int id);
	Customers selectCustomerById(int id);
	List<Customers> selectAllCustomer();

}
