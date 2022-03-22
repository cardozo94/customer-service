package com.camcar.customer.repository;

public final class Queries {

	public final static String SELECT_DOCUMENTS_BY_CUSTOMER_ID = 
			"SELECT d FROM documents d WHERE id_customer = :idCustomer";
	public final static String SELECT_RIGHT_JOIN_DOCUMENT_CUSTOMER_BY_CUSTOMER_ID =
			"SELECT new com.camcar.customer.repository.dto.CustomerDocumentData(c, d) FROM documents d RIGHT JOIN d.customer c WHERE c.id = :idCustomer";
	public final static String SELECT_RIGHT_JOIN_DOCUMENT_CUSTOMER = 
			"SELECT new com.camcar.customer.repository.dto.CustomerDocumentData(c, d) FROM documents d RIGHT JOIN d.customer c";
	public final static String SELECT_CUSTOMERS = "SELECT c FROM customers c";
	public final static String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = :id";
	}
