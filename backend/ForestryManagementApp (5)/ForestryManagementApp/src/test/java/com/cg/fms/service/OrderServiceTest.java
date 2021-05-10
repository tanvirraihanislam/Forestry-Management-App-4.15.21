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
import com.cg.fms.dao.OrderDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Order;
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	OrderService a;
	
	@MockBean
	OrderDao dao;
	
	
	@Test
	public void addOrderservice() {
		Order a = new Order();
		a.setOrderNumber("10a");
		a.setDeliveryDate("2021-05-22");
		a.setDeliveryPlace("hyderabad");
		a.setQuantity("20");
		a.setCustomer(null);
		a.setScheduler(null);
		when(dao.save(a)).thenReturn(a);
	}
	
	
	
	
	@Test
	public void updateOrderservice() {
		Optional<Order> service = dao.findById("10a");
		if(service.isPresent()) {
			service.get().setQuantity("20");
			dao.save(service.get());	
		}
		Optional<Order> updatedservice = dao.findById("10");
		if(updatedservice.isPresent()) {
			assertThat(updatedservice.get().getQuantity().equals("20"));
		}
	}
	
	
	@Test
	public void getOrderServiceById() {
		Optional<Order> service = dao.findById("10");;
		if(service.isPresent()) {
			assertEquals(service.get().getOrderNumber(), "10");
		}
	}
	
	
	@Test
	public void getAllOrderservice() {
		Mockito.when(dao.findAll())
		.thenReturn(java.util.stream.Stream.of(new Order(),new Order()).collect(Collectors.toList()));
		
		assertEquals(2, a.getAllOrders().size());
		verify(dao, times(1)).findAll();
		
	}
	
	
			
			public void deleteOrderservice() {
				String ServiceId = "10";
				a.deleteOrder(ServiceId);
				
				verify(dao, times(1)).deleteById( ServiceId);
			
			}
}