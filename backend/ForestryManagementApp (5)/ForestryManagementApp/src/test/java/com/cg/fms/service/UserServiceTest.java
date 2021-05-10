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

import com.cg.fms.dao.OrderDao;
import com.cg.fms.dao.UserDao;
import com.cg.fms.dto.Order;
import com.cg.fms.dto.User;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService a;
	
	@MockBean
	UserDao dao;
	
	@Test
	public void addUserservice() {
		User o = new User();
		o.setUserName("10a");
		o.setPassword("2021-05-22");
		o.setRole("hyderabad");
		when(dao.save(o)).thenReturn(o);
	}
	
	@Test
	public void removeUserservice() {
		String ServiceId = "j";
		a.removeUser(ServiceId);
		verify(dao, times(0)).deleteById(ServiceId);
	
	}
	
	@Test
	public void getAllUserservice() {
		Mockito.when(dao.findAll())
		.thenReturn(java.util.stream.Stream.of(new User(),new User()).collect(Collectors.toList()));
		
		assertEquals(2, a.getAllUsers().size());
		verify(dao, times(1)).findAll();
		
	}
	
	
	
	
	

}