package com.camcar.customer.repository;

public final class Queries {

	public final static String SELECT_DOCUMENTS_BY_CUSTOMER_ID = 
			"SELECT d FROM documents d WHERE id_customer = :idCustomer";
	public final static String SELECT_LEFT_JOIN_DOCUMENT_CUSTOMER_BY_CUSTOMER_ID =
			"SELECT c FROM customers c LEFT JOIN documents d ON c.id = d.idCustomer WHERE c.id = :idCustomer";
	public final static String SELECT_RIGHT_JOIN_DOCUMENT_CUSTOMER = 
			"SELECT c FROM customers c";
	public final static String SELECT_CUSTOMERS = "SELECT c FROM customers c";
	public final static String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = :id";
	}
