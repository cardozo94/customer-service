package com.camcar.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camcar.customer.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findById(int id);
}