package com.camcar.customer.infrastructure.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.camcar.customer.infrastructure.repository.SpringDataCustomerRepository;

@EnableJpaRepositories(basePackageClasses =  SpringDataCustomerRepository.class)
public class SqlServerDbConfiguration {
	
}
