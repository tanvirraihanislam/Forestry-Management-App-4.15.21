package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Customer;
import com.cg.fms.dto.Land;
import com.cg.fms.service.CustomerService;


@RestController
@RequestMapping("customerDetails")

public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@GetMapping("{CustomerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("CustomerId") String customerId){
		
		Customer c = customerservice.getCustomer(customerId);
		if(c != null) {
			return new ResponseEntity<Customer>(c, HttpStatus.FOUND);
			
		}
		else {
			return new ResponseEntity<String>("Contract details of " + customerId + " not found", HttpStatus.NOT_FOUND );
		}
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		return customerservice.getAllCustomers();
	}
	
	@PutMapping("{customerId}")
	public String updateCustomer(@PathVariable("customerId") String customerId,  String customerName, String customerType,
			 String customerPassword, String customerEmail, String customerAddress,
			 String customerTown, String customerPostalCode, String customerContact) {

		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName(customerName);
		customer.setCustomerType(customerType);
		customer.setCustomerPassword(customerPassword);
		customer.setCustomerEmail(customerEmail);
		customer.setCustomerAddress(customerAddress);
		customer.setCustomerTown(customerTown);
		customer.setCustomerPostalCode(customerPostalCode);
		customer.setCustomerContact(customerContact);
		
		if(customerservice.updateCustomer(customer))
			return "Customer has been updated";
		else
			return "Customer Not found";
	}
	
	@PostMapping("{customerId}/{customerName}/{customerType}/{customerPassword}/{customerEmail}/{customerAddress}/{customerTown}/{customerPostalCode}/{customerContact}")
	public String addCustomer(@PathVariable("customerId") String customerId, @PathVariable("customerName") String customerName, @PathVariable("customerType") String customerType,
			@PathVariable("customerPassword") String customerPassword,@PathVariable("customerEmail") String customerEmail,@PathVariable("customerAddress") String customerAddress,
			@PathVariable("customerTown") String customerTown,@PathVariable("customerPostalCode") String customerPostalCode, @PathVariable("customerContact") String customerContact) {
		Customer a = new Customer();
		a.setCustomerId(customerId);
		a.setCustomerName(customerName);
		a.setCustomerType(customerType);
		a.setCustomerPassword(customerPassword);
		a.setCustomerEmail(customerEmail);
		a.setCustomerAddress(customerAddress);
		a.setCustomerTown(customerTown);
		a.setCustomerPostalCode(customerPostalCode);
		a.setCustomerContact(customerContact);
		if(customerservice.addCustomer(a)) {
			return "New Customer added";
		}
		else {
			return "Customer already exists";
		}
	}
	
	@DeleteMapping("{customerId}")
	public String deleteCustomer(@PathVariable("customerId") String customerId) {
		if(customerservice.deleteCustomer(customerId)) {
			return "Customer has been removed";
		}
		else {
			return "Customer not found";
		}
	}
	
	@GetMapping("{customerId}/{customerPassword}")
	public String login(@PathVariable("customerId") String customerId, @PathVariable("customerPassword") String customerPassword) {
		return customerservice.login(customerId, customerPassword);
		
	}
	
	
	
}
