package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.CustomerDao;
import com.cg.fms.dto.Customer;
import com.cg.fms.exception.CustomerException;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao ado;

	@Override
	public Customer getCustomer(String customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> opt=ado.findById(customerId);
		if(opt.isPresent())
			return opt.get();
		return null;
		
	}

	@Override
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(ado.existsById(customer.getCustomerId())) {
			throw new CustomerException("customer with number "+customer.getCustomerId()+" already exists");
		}else {
			ado.save(customer);
			return true;
		}
		
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(ado.existsById(customer.getCustomerId())) {
			ado.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(String customerId) {
		// TODO Auto-generated method stub
		if(ado.existsById(customerId)) {
			ado.deleteById(customerId);
			return true;
		}
		return false;	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return (List<Customer>)ado.findAll();
	}

	@Override
	public String login(String customerId, String customerPassword) {
		// TODO Auto-generated method stub
		return null;
	}
}
