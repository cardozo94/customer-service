package com.camcar.customer.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.camcar.customer.domain.Customers;

//@Repository
public interface SpringDataCustomerRepository extends JpaRepository<Customers, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customers (name, address, phone_number) VALUES (:#{#customer.name}, :#{#customer.address}, :#{#customer.phoneNumber})", nativeQuery = true)
	void insertCustomer(@Param("customer") Customers customer);

	@Transactional
	@Modifying
	@Query("UPDATE customers SET name = :#{#customer.name}, address = :#{#customer.address}, phone_number = :#{#customer.phoneNumber} WHERE id = :#{#customer.id}")
	void updateCustomer(@Param("customer") Customers customer);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers WHERE id = :id", nativeQuery = true)
	void deleteCustomer(@Param("id") int id);

	@Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
	Customers findById(@Param("id") int id);

	@Query("SELECT c FROM customers c")
	List<Customers> selectAllCustomers();
}
