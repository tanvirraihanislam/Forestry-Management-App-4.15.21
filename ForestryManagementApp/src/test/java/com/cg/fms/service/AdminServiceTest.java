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
import com.cg.fms.dto.Admin;

@SpringBootTest
public class AdminServiceTest {
	@Autowired
	AdminService a;
	
	@MockBean
	AdminDao dao;
	
	
		@Test
		public void addAdminservice() {
			Admin a = new Admin();
			a.setAdminId("10");
			a.setAdminName("esubabu");
			a.setAdminPassword("esu123");
			when(dao.save(a)).thenReturn(a);
		}
		
		
		
		
		@Test
		public void updateAdminservice() {
			Optional<Admin> service = dao.findById("10");
			if(service.isPresent()) {
				service.get().setAdminName("esubabu");
				dao.save(service.get());	
			}
			Optional<Admin> updatedadminservice = dao.findById("10");
			if(updatedadminservice.isPresent()) {
				assertThat(updatedadminservice.get().getAdminName().equals("esubabu"));
			}
		}
		
		
		@Test
		public void getAdminServiceById() {
			Optional<Admin> service = dao.findById("10");;
			if(service.isPresent()) {
				assertEquals(service.get().getAdminId(), "10");
			}
		}
		
		
		@Test
		public void getAllAdminservice() {
			Mockito.when(dao.findAll())
							.thenReturn(java.util.stream.Stream.of(new Admin(),new Admin()).collect(Collectors.toList()));
			
			assertEquals(2, a.getAllAdmins().size());
			verify(dao, times(1)).findAll();
			
		}
		
		
				
				public void deleteAdminservice() {
					String ServiceId = "10";
					a.deleteAdmin(ServiceId);
					
					verify(dao, times(1)).deleteById( ServiceId);
				}

}