package com.camcar.customer.domain.service;

import java.util.List;
import java.util.Optional;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.repository.CustomerRepository;

import io.reactivex.Single;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Single<Boolean> createCustomer(Customers customer) {
		return Single.create(single -> {
			customerRepository.save(customer);
			single.onSuccess(true);
		});
	}

	@Override
	public Single<Boolean> updateCustomer(int id, Customers customer) {
		return Single.create(single -> {
			Customers customerData = customerRepository.findById(id).get();
			if (customerData != null) {
				customerData.setName(customer.getName());
				customerData.setAddress(customer.getAddress());
				customerData.setPhoneNumber(customer.getPhoneNumber());
				customerRepository.save(customerData);
				single.onSuccess(true);
			}else {
				single.onSuccess(false);
			}
		});
	}

	@Override
	public Single<Boolean> deleteCustomer(int id) {
		return Single.create(single -> {
			customerRepository.deleteById(id);
			single.onSuccess(true);
		});
	}

	@Override
	public Single<Customers> selectCustomerById(int id) {
		return Single.create(single -> {
			Customers customer = customerRepository.findById(id).get();
			single.onSuccess(customer);
		});
	}

	@Override
	public Single<List<Customers>> selectAllCustomers() {
		return Single.create(single -> {
			List<Customers> customers = customerRepository.findAll();
			single.onSuccess(customers);
		});
	}

}
