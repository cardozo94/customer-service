package com.camcar.customer.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponse {
	
	private int id;
	private String type;
	private String value;
	private int idCustomer;

}
