package com.camcar.customer.repository.dto;

import com.camcar.customer.model.Customer;
import com.camcar.customer.model.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDocumentData {
	
	private Customer customer;
	private Document document;
	
	public CustomerDocumentData(Customer customer, Document document) {
		if(document!=null)
			this.document = document;
		this.customer = customer;

	}

}
