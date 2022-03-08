package com.camcar.customer.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camcar.customer.domain.repository.CustomerRepository;
import com.camcar.customer.domain.service.CustomerService;
import com.camcar.customer.domain.service.CustomerServiceImpl;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public CustomerService cutomerService(CustomerRepository customerRepository) {
		return new CustomerServiceImpl(customerRepository);
	}

}
