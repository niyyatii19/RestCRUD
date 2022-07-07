package com.restApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.restApi.dao.CustomerRepository;
import com.restApi.dto.CustomerDTO;
import com.restApi.entity.Customer;
import com.restApi.exception.CustomerNotFoundException;
import com.restApi.exception.ExceptionConstants;

@Service
@PropertySource("classpath:message.properties")
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	Environment environment;
	
	
	public String createCustomer(CustomerDTO customer) {
		Customer cust = new Customer();
		cust.setName(customer.getName());
		cust.setEmail(customer.getEmail());
		cust.setDateOfBirth(customer.getDateOfBirth());
		customerRepo.save(cust);
		return "success.message";
	}
	
	public String updateCustomer(CustomerDTO email, Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepo.findById(id);
		if(customer.isPresent()) {
			Customer cust = customer.get();
			cust.setEmail(email.getEmail());
			customerRepo.saveAndFlush(cust);
			return "update.message";
		}else {
			throw new CustomerNotFoundException(environment.getProperty(ExceptionConstants.CUSTOMER_NOT_FOUND.toString()));
		}
	}
	
	public String deleteCustomer(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepo.findById(id);
		if(customer.isPresent()) {
			Customer cust = customer.get();
			customerRepo.delete(cust);
			return "delete.message";
		}else {
			throw new CustomerNotFoundException(environment.getProperty(ExceptionConstants.CUSTOMER_NOT_FOUND.toString()));
		}
	}
	
	public List<CustomerDTO> getAllCustomer() {
		List<Customer> allCustomers = customerRepo.findAll();
		List<CustomerDTO> customers = new ArrayList<>();
		for(Customer c: allCustomers) {
			CustomerDTO cust = new CustomerDTO();
			cust.setName(c.getName());
			cust.setDateOfBirth(c.getDateOfBirth());
			cust.setEmail(c.getEmail());
			customers.add(cust);
		}
		
		return customers;
	}
	
	public CustomerDTO getCustomerById(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepo.findById(id);
		if(customer.isPresent()) {
			Customer cust = customer.get();
			CustomerDTO custom = new CustomerDTO();
			custom.setName(cust.getName());
			custom.setEmail(cust.getEmail());
			custom.setDateOfBirth(cust.getDateOfBirth());
			return custom;
		}else {
			throw new CustomerNotFoundException(environment.getProperty(ExceptionConstants.CUSTOMER_NOT_FOUND.toString()));
		}
	}
}
