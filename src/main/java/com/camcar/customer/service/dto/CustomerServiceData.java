package com.camcar.customer.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerServiceData {

	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private List<DocumentServiceData> documents;
	
}
