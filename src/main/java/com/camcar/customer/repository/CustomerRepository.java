package com.camcar.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.camcar.customer.model.Customer;

//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customers (name, address, phone_number) VALUES (:name, :address, :phoneNumber)", nativeQuery = true)
	void insertCustomer(@Param("name") String name, @Param("address") String address,
			@Param("phoneNumber") String phoneNumber);
	
	@Transactional
	@Modifying
	@Query("UPDATE customers SET name = :name, address = :address, phone_number = :phoneNumber WHERE id = :id")
	void updateCustomer(@Param("id") int id, @Param("name") String name, @Param("address") String address,
			@Param("phoneNumber") String phoneNumber);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers WHERE id = :id", nativeQuery = true)
	void deleteCustomer(@Param("id") int id);

	@Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
	Customer findById(@Param("id") int id);
	
	@Query("SELECT c FROM customers c")
	List<Customer> selectAllCustomers();
}