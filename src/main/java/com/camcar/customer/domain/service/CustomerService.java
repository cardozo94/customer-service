package com.camcar.customer.domain.service;

import java.util.List;

import com.camcar.customer.domain.service.dto.CustomerServiceDto;

public interface CustomerService {
	
	boolean createCustomer(CustomerServiceDto customer);
	boolean updateCustomer(int d, CustomerServiceDto customer);
	boolean deleteCustomer(int id);
	CustomerServiceDto selectCustomerById(int id);
	List<CustomerServiceDto> selectAllCustomers();

}
