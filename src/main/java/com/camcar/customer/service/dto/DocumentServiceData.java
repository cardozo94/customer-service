package com.camcar.customer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentServiceData {

	private int id;
	private String type;
	private String value;
	private int idCustomer;
}
