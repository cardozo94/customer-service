package com.camcar.customer.controller;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.camcar.customer.controller.dto.CustomerRequest;
import com.camcar.customer.controller.dto.CustomerResponse;
import com.camcar.customer.service.CustomerServiceImpl;
import com.camcar.customer.service.ServiceDefinition;
import com.camcar.customer.service.dto.CustomerServiceData;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ServiceDefinition<CustomerServiceData> customerService;
//	private ModelMapper mapper = new ModelMapper();
	@Autowired
	private ConversionService converter;
//	private CustomerRequestConverter converterReq = new CustomerRequestConverter();
//	private CustomerResponseConverter converterRsp = new CustomerResponseConverter();

	@GetMapping("/test")
	public ResponseEntity<String> test() throws Exception {

		CustomerRequest customer = CustomerRequest.builder().name("Camilo").address("Carrera 14 # 9 -62")
				.phoneNumber("3105504647").build();
		if(customer.getName().equals("Camilo"))
			throw new Exception();
		return new ResponseEntity<String>(customer.getName(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerResponse createCustomer(@RequestBody CustomerRequest customerReq) {
		return converter.convert(customerService.create(converter.convert(customerReq, CustomerServiceData.class)),
				CustomerResponse.class);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable("id") int id, @RequestBody CustomerRequest customerReq) {
		if (!customerService.update(id, converter.convert(customerReq, CustomerServiceData.class)))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
	}

	@GetMapping
	public List<CustomerResponse> getAllCustomers() {
		return customerService.selectAll().stream().map(customer -> converter.convert(customer, CustomerResponse.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable("id") int id) {
		CustomerResponse customer = converter.convert(customerService.selectById(id), CustomerResponse.class);
		if (customer == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
		return customer;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("id") int id) {
		if (!customerService.delete(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
	}

	@GetMapping("/documentInfo")
	public List<CustomerResponse> getAllCustomersInfo() {
		return ((CustomerServiceImpl) customerService).selectAllInfoFromAllCustomers().stream()
				.map(customer -> converter.convert(customer, CustomerResponse.class)).toList();
	}

	@GetMapping("/documentInfo/{id}")
	public CustomerResponse getAllInfoCustomer(@PathVariable("id") int id) {
		CustomerResponse customer = converter.convert(((CustomerServiceImpl) customerService).selectAllInfoCustomer(id),
				CustomerResponse.class);
		if (customer == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not Found");
		}
		return customer;
	}

	@PostMapping("/documentInfo")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerResponse createCustomerWithAllInfo(@RequestBody CustomerRequest customerReq) {
		CustomerResponse customer = converter.convert(((CustomerServiceImpl) customerService).insertFullInfoCustomer(
				converter.convert(customerReq, CustomerServiceData.class)), CustomerResponse.class);
		if (customer == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Info is not correct.");
		return customer;
	}
}
