package com.camcar.customer.controller.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
	
	private String name;
	private String address;
	private String phoneNumber;
	private List<DocumentRequest> documents;

}
