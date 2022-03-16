package com.camcar.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camcar.customer.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	Document findById(int id);
	
	@Query("SELECT d FROM documents d WHERE id_customer = :idCustomer")
	List<Document> findByCustomerId(int idCustomer);

}