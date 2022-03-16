package com.camcar.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camcar.customer.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	Document findById(int id);

}