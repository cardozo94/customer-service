package com.camcar.customer.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camcar.customer.domain.Customers;

@Repository
public interface SpringDataCustomerRepository extends JpaRepository<Customers, Integer> {

}
