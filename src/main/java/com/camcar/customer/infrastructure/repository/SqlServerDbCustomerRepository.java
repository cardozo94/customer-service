package com.camcar.customer.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.repository.CustomerRepository;

@Component
@Primary
public class SqlServerDbCustomerRepository implements CustomerRepository {
	
	private final SpringDataCustomerRepository customerRepository;
	
	@Autowired
	public SqlServerDbCustomerRepository(final SpringDataCustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Optional<Customers> findById(int id) {
		return Optional.ofNullable(customerRepository.findById(id));
	}

	public void insert(Customers customerData) {
		customerRepository.insertCustomer(customerData);
	}

	@Override
	public void deleteById(int id) {
		customerRepository.deleteCustomer(id);		
	}

	@Override
	public List<Customers> findAll() {
		return customerRepository.selectAllCustomers();
	}

	@Override
	public void update(Customers customerData) {
		customerRepository.updateCustomer(customerData);		
	}

}
