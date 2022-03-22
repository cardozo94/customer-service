package com.camcar.customer.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentRequest {
	
	private String type;
	private String value;
	private int idCustomer;

}
