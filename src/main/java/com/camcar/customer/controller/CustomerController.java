package com.camcar.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.camcar.customer.model.Customer;
import com.camcar.customer.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 

	@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String test() {
		System.out.println("pase por get 2");
		
		Customer customer = new Customer();
//		customer.setId(1);
		customer.setName("Camilo");
		customer.setAddress("Carrera 14 # 9 -62");
		customer.setPhoneNumber("3105504647");
        return customer.getName();
    }
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
	
	@RequestMapping(value = "/obtainAll", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() {
		return customerService.selectAllCustomers();
	}
	
	@RequestMapping(value = "/obtainById/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable("id") int id) {
		return customerService.selectCustomerById(id);
	}
	
	@RequestMapping(value = "/clean/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteCustomer(@PathVariable("id") int id) {
		return customerService.deleteCustomer(id);
	}
}
