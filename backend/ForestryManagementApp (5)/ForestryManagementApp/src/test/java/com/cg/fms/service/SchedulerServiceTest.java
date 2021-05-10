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
import com.cg.fms.dao.SchedulerDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Scheduler;
@SpringBootTest
public class SchedulerServiceTest {
	@Autowired
	SchedulerService a;
	
	@MockBean
	SchedulerDao dao;
	
	
	@Test
	public void addSchedulerservice() {
		Scheduler a = new Scheduler();
		a.setSchedulerId("20");
		a.setSchedulerContact("8888899999");
		a.setSchedulerName("rajesh");
		a.setTruckNumber("ap50k");
		a.setOrders(null);
		when(dao.save(a)).thenReturn(a);
	}
	
	@Test
	public void updateSchedulerservice() {
		Optional<Scheduler> service = dao.findById("10");
		if(service.isPresent()) {
			service.get().setSchedulerName("rajesh");
			dao.save(service.get());	
		}
		Optional<Scheduler> updatedadminservice = dao.findById("10");
		if(updatedadminservice.isPresent()) {
			assertThat(updatedadminservice.get().getSchedulerName().equals("rajesh"));
		}
	}
	
	
	@Test
	public void getSchedulerServiceById() {
		Optional<Scheduler> service = dao.findById("10");;
		if(service.isPresent()) {
			assertEquals(service.get().getSchedulerId(), "10");
		}
	}
	
	
	@Test
	public void getAllSchedulerservice() {
		Mockito.when(dao.findAll())
		.thenReturn(java.util.stream.Stream.of(new Scheduler(),new Scheduler()).collect(Collectors.toList()));
		
		assertEquals(2, a.getAllSchedulers().size());
		verify(dao, times(1)).findAll();
		
	}
	
	@Test		
	public void deleteSchedulerservice() {
		String ServiceId = "10";
		a.deleteScheduler(ServiceId);
				
		verify(dao, times(0)).deleteById( ServiceId);
	}

}