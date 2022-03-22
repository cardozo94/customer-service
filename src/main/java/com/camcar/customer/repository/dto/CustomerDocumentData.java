package com.camcar.customer.repository.dto;

import com.camcar.customer.repository.model.Customer;
import com.camcar.customer.repository.model.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDocumentData {
	
	private Customer customer;
	private Document document;
	
	public CustomerDocumentData(Customer customer, Document document) {
		if(document!=null)
			this.document = document;
		this.customer = customer;

	}

}
