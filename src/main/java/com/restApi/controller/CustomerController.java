package com.restApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.restApi.dto.CustomerDTO;
import com.restApi.exception.CustomerNotFoundException;
import com.restApi.service.CustomerService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/customer")
@PropertySource("classpath:message.properties")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value = "/getAllCustomer")
	public List<CustomerDTO> getAllCustomers(){
		List<CustomerDTO> customers = customerService.getAllCustomer();
		return customers;
	}
	
	@PostMapping(value = "/createCustomer")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customer) {
		String message = customerService.createCustomer(customer);
		return new ResponseEntity<String>(environment.getProperty(message), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/customerById/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException{
		CustomerDTO cust = customerService.getCustomerById(id);
		return new ResponseEntity<CustomerDTO>(cust, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestParam("id") Long id, @RequestBody CustomerDTO email) 
			throws CustomerNotFoundException{
		String cust = customerService.updateCustomer(email, id);
		return new ResponseEntity<String>(environment.getProperty(cust), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteCustomer")
	public ResponseEntity<String> deleteCustomer(@RequestParam("id") Long id) 
			throws CustomerNotFoundException{
		String cust = customerService.deleteCustomer(id);
		return new ResponseEntity<String>(environment.getProperty(cust), HttpStatus.OK);
	}
	
	
}
