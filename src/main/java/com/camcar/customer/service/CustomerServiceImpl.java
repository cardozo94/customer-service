package com.camcar.customer.service;

import java.util.List;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camcar.customer.model.Customer;
import com.camcar.customer.repository.CustomerRepository;
import com.camcar.customer.service.converters.CustomerConverter;
import com.camcar.customer.service.converters.CustomerServiceConverter;
import com.camcar.customer.service.dto.CustomerServiceDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
//	private ModelMapper mapper = new ModelMapper();
	private CustomerServiceConverter converterToCustomerService = new CustomerServiceConverter();
	private CustomerConverter conterterToCustomer = new CustomerConverter();

	@Override
	public boolean createCustomer(CustomerServiceDto customer) {
		boolean result = false;
		try {
			customerRepository.insertCustomer(conterterToCustomer.convert(customer));
			result = true;
		} catch (IllegalArgumentException e) {
		}
		return result;
	}

	@Override
	public boolean updateCustomer(int id, CustomerServiceDto customer) {
		boolean result = false;
		Customer customerData = customerRepository.findById(id);
		if (customerData != null) {
			customerData.setName(customer.getName());
			customerData.setAddress(customer.getAddress());
			customerData.setPhoneNumber(customer.getPhoneNumber());
			customerRepository.updateCustomer(customerData);
			result = true;
		}
		return result;
	}

	@Override
	public boolean deleteCustomer(int id) {
		boolean result = false;
		try {
			customerRepository.deleteCustomer(id);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CustomerServiceDto selectCustomerById(int id) {
		Customer customerRepo = customerRepository.findById(id);
		CustomerServiceDto customer;
		if (customerRepo != null)
			customer = converterToCustomerService.convert(customerRepo);
		else
			customer = new CustomerServiceDto(0, "Not found", "-", "-");
		return customer;
	}

	@Override
	public List<CustomerServiceDto> selectAllCustomers() {
		return customerRepository.selectAllCustomers().stream()
				.map(customer -> converterToCustomerService.convert(customer)).collect(Collectors.toList());
	}

}