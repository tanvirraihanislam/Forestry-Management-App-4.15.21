package com.cg.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Customer;
import com.cg.fms.dto.Land;
import com.cg.fms.dto.Scheduler;
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;
import com.cg.fms.service.AdminService;
import com.cg.fms.service.CustomerService;
import com.cg.fms.service.SchedulerService;

import io.jsonwebtoken.SignatureException;

@RestController
@RequestMapping("customerDetails")
@CrossOrigin(origins = "http://localhost:4200")

public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@Autowired
	AdminService adServ;

	@Autowired
	SchedulerService schedServ;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("{CustomerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("CustomerId") String customerId,HttpServletRequest request) {
		validateToken(request);
		Customer c = customerservice.getCustomer(customerId);
		if (c != null) {
			return new ResponseEntity<Customer>(c, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("Customer details of " + customerId + " not found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public List<Customer> getAllCustomers(HttpServletRequest request) {
		validateToken(request);
		return customerservice.getAllCustomers();
	}
	
	@PutMapping()
	public String updateCustomer(@Valid @RequestBody Customer customer,HttpServletRequest request) {
		validateToken(request);
		if (customerservice.updateCustomer(customer))
			return "Customer has been updated";
		else
			return "Customer Not found";
	}

	@PutMapping("{customerId}")
	public String updateCustomer(@PathVariable("customerId") String customerId, String customerName,
			String customerType, String customerPassword, String customerEmail, String customerAddress,
			String customerTown, String customerPostalCode, String customerContact, String adminId,
			String schedulerId,HttpServletRequest request) {
		validateToken(request);
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
		
		Admin admin = new Admin();
		admin = adServ.getAdmin(adminId);
		a.setAdmin(admin);

		Scheduler sched = new Scheduler();
		sched = schedServ.getScheduler(schedulerId);
		a.setScheduler(sched);
		
		if (customerservice.updateCustomer(a))
			return "Customer has been updated";
		else
			return "Customer Not found";
	}

	@PostMapping()
	public String addCustomer(@Valid @RequestBody Customer c,HttpServletRequest request) {
		
		
		if (customerservice.addCustomer(c)) {
			return "New Customer added";
		} else {
			return "Customer already exists";
		}
	}

	@DeleteMapping("{customerId}")
	public String deleteCustomer(@PathVariable("customerId") String customerId,HttpServletRequest request) {
		validateToken(request);
		if (customerservice.deleteCustomer(customerId)) {
			return "Customer has been removed";
		} else {
			return "Customer not found";
		}
	}

	@GetMapping("{customerId}/{customerPassword}")
	public String login(@PathVariable("customerId") String customerId,
			@PathVariable("customerPassword") String customerPassword,HttpServletRequest request) {
		validateToken(request);
		return customerservice.login(customerId, customerPassword);

	}
	
	public void validateToken(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new UserNotFoundException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new UserNotFoundException("Invalid Token");

		jwtToken = tokenHeader.substring(7);
		try {
			if (!(jwtTokenUtil.validateToken(jwtToken)))
				throw new UserNotFoundException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new UserNotFoundException("Invalid Token");
		}
	}

}
