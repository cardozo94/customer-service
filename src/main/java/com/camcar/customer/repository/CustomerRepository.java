package com.camcar.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.camcar.customer.model.Customer;
import com.camcar.customer.repository.dto.CustomerDocumentData;

//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO customers (name, address, phone_number) VALUES (:#{#customer.name}, :#{#customer.address}, :#{#customer.phoneNumber})", nativeQuery = true)
	void insertCustomer(@Param("customer") Customer customer);

	@Transactional
	@Modifying
	@Query("UPDATE customers SET name = :#{#customer.name}, address = :#{#customer.address}, phone_number = :#{#customer.phoneNumber} WHERE id = :#{#customer.id}")
	void updateCustomer(@Param("customer") Customer customer);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers WHERE id = :id", nativeQuery = true)
	void deleteCustomer(@Param("id") int id);

	@Query(value = "SELECT * FROM customers WHERE id = :id", nativeQuery = true)
	Customer findById(@Param("id") int id);

	@Query("SELECT c FROM customers c")
	List<Customer> selectAllCustomers();
	
	@Query("SELECT new com.camcar.customer.repository.dto.CustomerDocumentData(c, d) FROM documents d RIGHT JOIN d.customer c")
	List<CustomerDocumentData> selectAllInfoForAllCustomers();
	
	@Query("SELECT new com.camcar.customer.repository.dto.CustomerDocumentData(c, d) FROM documents d RIGHT JOIN d.customer c WHERE c.id = :id")
	CustomerDocumentData findByIdAllInfoCustomer(@Param("id") int id);
}