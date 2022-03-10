package com.camcar.customer.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.repository.CustomerRepository;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

import io.reactivex.Single;

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private ModelMapper mapper;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		mapper = new ModelMapper();
	}

	@Override
	public Single<Boolean> createCustomer(CustomerServiceDto customer) {
		return Single.create(single -> {
			customerRepository.save(mapper.map(customer, Customers.class));
			single.onSuccess(true);
		});
	}

	@Override
	public Single<Boolean> updateCustomer(int id, CustomerServiceDto customer) {
		return Single.create(single -> {
			Customers customerData = customerRepository.findById(id).get();
			if (customerData != null) {
				customerData.setName(customer.getName());
				customerData.setAddress(customer.getAddress());
				customerData.setPhoneNumber(customer.getPhoneNumber());
				customerRepository.save(customerData);
				single.onSuccess(true);
			} else {
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
	public Single<Object> selectCustomerById(int id) {
		return Single.create(single -> {
			CustomerServiceDto customer = mapper.map(customerRepository.findById(id).get(), CustomerServiceDto.class);
			single.onSuccess(customer);
		}).onErrorReturn(error -> new CustomerServiceDto(0, "not Found", "-", "-"));
	}

	@Override
	public Single<List<CustomerServiceDto>> selectAllCustomers() {
		return Single.create(single -> {
			List<CustomerServiceDto> customers = customerRepository.findAll().stream()
					.map(customer -> mapper.map(customer, CustomerServiceDto.class)).collect(Collectors.toList());
			single.onSuccess(customers);
		});
	}

}
