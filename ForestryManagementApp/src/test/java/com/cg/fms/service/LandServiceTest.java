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
import com.cg.fms.dao.LandDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Land;

@SpringBootTest
public class LandServiceTest {
	
	@Autowired
	LandService landservice;
	
	@MockBean
	LandDao landdao;
	
	@Test
	public void addLandService() {
		
		Land land = new Land();
		
		land.setLandId("L01");
		land.setSurveyNumber("BT05GH");
		land.setOwnerName("Abir");
		land.setLandArea("2596");
		land.setContract(null);
		
		when(landdao.save(land)).thenReturn(land);
	}
	
	@Test
	
	public void updateAdminService() {
		
		Optional<Land> service = landdao.findById("L01");
		
		if(service.isPresent()) {
			service.get().setLandArea("6000");
			landdao.save(service.get());	
		}
		Optional<Land> updatedadminservice = landdao.findById("L01");
		if(updatedadminservice.isPresent()) {
			assertThat(updatedadminservice.get().getLandArea().equals("6000"));
		}
	}
	
	@Test
	public void getLandServiceById() {
		
		Optional<Land> service = landdao.findById("L01");;
		if(service.isPresent()) {
			assertEquals(service.get().getLandArea(), "2596");
		}
	}
	
	@Test
	public void getAllLandService() {
		
		Mockito.when(landdao.findAll())
						.thenReturn(java.util.stream.Stream.of(new Land(),new Land()).collect(Collectors.toList()));

		assertEquals(2, landservice.getAllLands().size());
		verify(landdao, times(1)).findAll();
	}
	
	@Test
	public void deleteLandService() {
		
		String ServiceId = "L01";
		landservice.removeLandDetails(ServiceId);
		
		verify(landdao, times(0)).deleteById( ServiceId);
	}
	
	
	
	
}
