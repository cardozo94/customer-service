package com.camcar.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
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

import com.camcar.customer.controller.converters.CustomerRequestConverter;
import com.camcar.customer.controller.converters.CustomerResponseConverter;
import com.camcar.customer.controller.dto.CustomerRequest;
import com.camcar.customer.controller.dto.CustomerResponse;
import com.camcar.customer.service.CustomerService;
//import com.camcar.customer.service.dto.CustomerServiceDto;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
//	private ModelMapper mapper = new ModelMapper();
	private CustomerRequestConverter converterReq = new CustomerRequestConverter();
	private CustomerResponseConverter converterRsp = new CustomerResponseConverter();

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
	public boolean createCustomer(@RequestBody CustomerRequest customerReq) {
		return customerService.createCustomer(converterReq.convert(customerReq));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable("id") int id, @RequestBody CustomerRequest customerReq) {
		if (!customerService.updateCustomer(id, converterReq.convert(customerReq)))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
	}

	@GetMapping
	public List<CustomerResponse> getAllCustomers() {
		return customerService.selectAllCustomers().stream().map(customer -> converterRsp.convert(customer))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable("id") int id) {
		CustomerResponse customer = converterRsp.convert(customerService.selectCustomerById(id));
		if (customer.getId() == 0)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
		return customer;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") int id) {
		if (!customerService.deleteCustomer(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
	}
}
