package com.camcar.customer.domain.service;

import java.util.List;

import com.camcar.customer.domain.service.dto.CustomerServiceDto;

import io.reactivex.Single;

public interface CustomerService {
	
	Single<Boolean> createCustomer(CustomerServiceDto customer);
	Single<Boolean> updateCustomer(int d, CustomerServiceDto customer);
	Single<Boolean> deleteCustomer(int id);
	Single<CustomerServiceDto> selectCustomerById(int id);
	Single<List<CustomerServiceDto>> selectAllCustomers();

}
