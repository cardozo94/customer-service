package com.camcar.customer.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camcar.customer.domain.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {

	@Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
	Customers findById(@Param("id") int id);

	@Query("SELECT c FROM customers c")
	List<Customers> selectAllCustomers();
	
	@Query(Queries.SELECT_RIGHT_JOIN_DOCUMENT_CUSTOMER)
	List<Customers> selectAllInfoForAllCustomers();
	
	@Query(Queries.SELECT_LEFT_JOIN_DOCUMENT_CUSTOMER_BY_CUSTOMER_ID)
	Customers findByIdAllInfoCustomer(@Param("idCustomer") int id);
}
