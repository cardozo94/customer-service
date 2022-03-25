package com.camcar.customer.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerResponse {

	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	private List<DocumentResponse> documents;
}