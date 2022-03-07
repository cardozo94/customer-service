package com.camcar.customer.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.camcar.customer.domain.Customers;
import com.camcar.customer.domain.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService; 

	@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String test() {
		System.out.println("pase por get 2");
		
		Customers customer = new Customers();
		customer.setId(1);
		customer.setName("Camilo");
		customer.setAddress("Carrera 14 # 9 -62");
		customer.setPhoneNumber("3105504647");
        return customer.getName();
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createCustomer(@RequestBody Customers customer) {
		return customerService.createCustomer(customer);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public boolean updateCustomer(@RequestBody Customers customer) {
		return customerService.updateCustomer(1, customer);
	}
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<Customers> getAllCustomers() {
		return customerService.selectAllCustomers();
	}
}
