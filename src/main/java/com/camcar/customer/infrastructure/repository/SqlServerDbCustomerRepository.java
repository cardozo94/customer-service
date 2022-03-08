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
		return customerRepository.findById(id);
	}

	@Override
	public void save(Customers customerData) {
		customerRepository.save(customerData);		
	}

	@Override
	public void deleteById(int id) {
		customerRepository.deleteById(id);		
	}

	@Override
	public List<Customers> findAll() {
		return customerRepository.findAll();
	}

}
