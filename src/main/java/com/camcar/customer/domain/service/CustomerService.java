package com.camcar.customer.domain.service;

import java.util.List;

import com.camcar.customer.domain.Customers;

import io.reactivex.Single;

public interface CustomerService {
	
	Single<Boolean> createCustomer(Customers customer);
	Single<Boolean> updateCustomer(int d, Customers customer);
	Single<Boolean> deleteCustomer(int id);
	Single<Customers> selectCustomerById(int id);
	Single<List<Customers>> selectAllCustomers();

}
