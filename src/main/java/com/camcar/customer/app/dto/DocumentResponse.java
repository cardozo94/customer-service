package com.camcar.customer.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponse {
	
	private int id;
	private String type;
	private String value;

}