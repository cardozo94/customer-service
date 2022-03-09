package com.camcar.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.camcar.customer.controller.dto.CustomerRequest;
import com.camcar.customer.model.Customer;
import com.camcar.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 

	@GetMapping("/test")
    public String test() {
		
		CustomerRequest customer = new CustomerRequest();
//		customer.setId(1);
		customer.setName("Camilo");
		customer.setAddress("Carrera 14 # 9 -62");
		customer.setPhoneNumber("3105504647");
        return customer.getName();
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		return customerService.selectAllCustomers();
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		return customerService.selectCustomerById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteCustomer(@PathVariable("id") int id) {
		return customerService.deleteCustomer(id);
	}
}
