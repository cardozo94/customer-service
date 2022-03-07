package com.camcar.customer.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camcar.customer.domain.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

	Customers findById(int id);
}
