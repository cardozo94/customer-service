package com.camcar.customer.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentServiceData {

	private int id;
	private String type;
	private String value;
	private int idCustomer;
}
