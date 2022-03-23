package com.camcar.customer.repository.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Setter(value = AccessLevel.NONE)
	private int id;
	private String name;
	private String address;
	private String phoneNumber;
	@OneToMany(targetEntity = Document.class)
	@JoinColumn(name = "idCustomer")
	private List<Document> document;

}
