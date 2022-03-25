package com.camcar.customer.infrastructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camcar.customer.domain.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	Document findById(int id);
	
	@Query(Queries.SELECT_DOCUMENTS_BY_CUSTOMER_ID)
	List<Document> findByCustomerId(int idCustomer);

}