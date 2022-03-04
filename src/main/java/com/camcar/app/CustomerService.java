package com.camcar.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.camcar.domain.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerService {

	@RequestMapping(value ="/test", method = RequestMethod.GET)
    public String test() {
		System.out.println("pase por get 2");
		
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Camilo");
		customer.setAddress("Carrera 14 # 9 -62");
		customer.setPhoneNumber("3105504647");
        return customer.getName();
    }
}
