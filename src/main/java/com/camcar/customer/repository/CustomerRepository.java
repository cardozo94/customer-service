package com.camcar.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.camcar.customer.repository.model.Customer;

//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//	@Transactional
//	@Modifying
//	@Query(value = "INSERT INTO customers (name, address, phone_number) VALUES (:#{#customer.name}, :#{#customer.address}, :#{#customer.phoneNumber})", nativeQuery = true)
//	void insertCustomer(@Param("customer") Customer customer);

//	@Transactional
//	@Modifying
//	@Query("UPDATE customers SET name = :#{#customer.name}, address = :#{#customer.address}, phone_number = :#{#customer.phoneNumber} WHERE id = :#{#customer.id}")
//	void updateCustomer(@Param("customer") Customer customer);

//	@Transactional
//	@Modifying
//	@Query(value = "DELETE FROM customers WHERE id = :id", nativeQuery = true)
//	void deleteCustomer(@Param("id") int id);

	@Query(value = Queries.SELECT_CUSTOMER_BY_ID, nativeQuery = true)
	Customer findById(@Param("id") int id);

	@Query(Queries.SELECT_CUSTOMERS)
	List<Customer> selectAllCustomers();
	
	@Query(Queries.SELECT_RIGHT_JOIN_DOCUMENT_CUSTOMER)
	List<Customer> selectAllInfoForAllCustomers();
	
	@Query(Queries.SELECT_LEFT_JOIN_DOCUMENT_CUSTOMER_BY_CUSTOMER_ID)
	Customer findByIdAllInfoCustomer(@Param("idCustomer") int id);
}