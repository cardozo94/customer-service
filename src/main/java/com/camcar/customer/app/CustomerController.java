package com.camcar.customer.app;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.server.ResponseStatusException;

import com.camcar.customer.app.dto.CustomerRequest;
import com.camcar.customer.app.dto.CustomerResponse;

import com.camcar.customer.domain.service.CustomerService;
import com.camcar.customer.domain.service.dto.CustomerServiceDto;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	private ModelMapper mapper = new ModelMapper();

	@GetMapping("/test")
	public String test() {
		System.out.println("pase por get 2");

		CustomerRequest customer = new CustomerRequest();
//		customer.setId(1);
		customer.setName("Camilo");
		customer.setAddress("Carrera 14 # 9 -62");
		customer.setPhoneNumber("3105504647");
		return customer.getName();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public boolean createCustomer(@RequestBody CustomerRequest customer) {
		return customerService.createCustomer(mapper.map(customer, CustomerServiceDto.class)).blockingGet();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable("id") int id, @RequestBody CustomerRequest customer) {
		if(!customerService.updateCustomer(id, mapper.map(customer, CustomerServiceDto.class)).blockingGet())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
	}

	@GetMapping
	public List<CustomerResponse> getAllCustomers() {
		return customerService.selectAllCustomers().blockingGet().stream()
				.map(customer -> mapper.map(customer, CustomerResponse.class)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable("id") int id) {
		CustomerResponse customer = mapper.map(customerService.selectCustomerById(id).blockingGet(), CustomerResponse.class);
		if(customer.getId() == 0)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
		return customer;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") int id) {
		if(!customerService.deleteCustomer(id).blockingGet())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found");
	}
}
