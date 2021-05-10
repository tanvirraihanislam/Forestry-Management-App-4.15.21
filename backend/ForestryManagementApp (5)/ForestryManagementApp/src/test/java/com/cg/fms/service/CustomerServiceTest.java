package com.cg.fms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.fms.dao.AdminDao;
import com.cg.fms.dao.CustomerDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Customer;
@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	CustomerService a;
	
	@MockBean
	CustomerDao dao;
	
	
	
	@Test
	public void addCustomerservice() {
		Customer a = new Customer();
		a.setCustomerId("10");
		a.setCustomerAddress("hyderabad");
		a.setCustomerContact("9999933333");
		a.setCustomerEmail("ram@gmail.com");
		a.setCustomerName("ram");
		a.setCustomerPassword("ram123");
		a.setCustomerPostalCode("530011");
		a.setCustomerTown("hyderabad");
		a.setCustomerType("customer");
		a.setAdmin(null);
		a.setScheduler(null);
		when(dao.save(a)).thenReturn(a);
	}
	
	
	
	
	@Test
	public void updateCustomerservice() {
		Optional<Customer> service = dao.findById("10");
		if(service.isPresent()) {
			service.get().setCustomerName("ram");
			dao.save(service.get());	
		}
		Optional<Customer> updatedCustomerservice = dao.findById("10");
		if(updatedCustomerservice.isPresent()) {
			assertThat(updatedCustomerservice.get().getCustomerName().equals("ram"));
		}
	}
	
	
	@Test
	public void getCustomerServiceById() {
		Optional<Customer> service = dao.findById("10");;
		if(service.isPresent()) {
			assertEquals(service.get().getCustomerId(), "10");
		}
	}
	
	
	@Test
	public void getAllCustomerservice() {
		Mockito.when(dao.findAll())
		.thenReturn(java.util.stream.Stream.of(new Customer(),new Customer()).collect(Collectors.toList()));
		
		assertEquals(2, a.getAllCustomers().size());
		verify(dao, times(1)).findAll();
		
	}
	
	
			
			public void deleteCustomerservice() {
				String ServiceId = "10";
				a.deleteCustomer(ServiceId);
				
				verify(dao, times(1)).deleteById( ServiceId);
			}

}