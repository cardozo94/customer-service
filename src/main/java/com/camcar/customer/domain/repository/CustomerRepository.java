package com.camcar.customer.domain.repository;

import java.util.List;
import java.util.Optional;

import com.camcar.customer.domain.Customers;

public interface CustomerRepository{

	Optional<Customers> findById(int id);

	void save(Customers customerData);

	void deleteById(int id);

	List<Customers> findAll();
}
