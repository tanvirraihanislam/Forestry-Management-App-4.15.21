package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.dto.Customer;
@Service
public interface CustomerService {
	
	public Customer getCustomer(String customerId);

	public boolean addCustomer(Customer customer);

	public boolean updateCustomer(Customer customer);

	public boolean deleteCustomer(String customerId);

	public List<Customer> getAllCustomers();
	
	public String login(String customerId,String customerPassword);
	
	
}