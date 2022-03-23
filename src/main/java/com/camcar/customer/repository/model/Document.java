package com.camcar.customer.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity(name="documents")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private int id;
	private String type;
	private String value;
//	@ManyToOne(targetEntity = Customer.class)
//	@JoinColumn(name = "id_customer")
	private int idCustomer;

}
