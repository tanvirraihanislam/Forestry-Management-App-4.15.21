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
import com.cg.fms.dao.ProductDao;
import com.cg.fms.dto.Admin;
import com.cg.fms.dto.Product;
@SpringBootTest
public class ProductServiceTest {
	@Autowired
	ProductService a;
	
	@MockBean
	ProductDao dao;
	
	@Test
	public void addProductservice() {
		Product a = new Product();
		a.setProductId("10");
		a.setProductDescription("Alphanso");
		a.setProductName("AlphansoMango");
		a.setProductQuantity("20");
		a.setOrder(null);
		when(dao.save(a)).thenReturn(a);
	}
	
	
	
	
	@Test
	public void updateProductservice() {
		Optional<Product> service = dao.findById("10");
		if(service.isPresent()) {
			service.get().setProductDescription("Alphanso");
			dao.save(service.get());	
		}
		Optional<Product> updatedservice = dao.findById("10");
		if(updatedservice.isPresent()) {
			assertThat(updatedservice.get().getProductDescription().equals("Alphanso"));
		}
	}
	
	
	@Test
	public void getProductServiceById() {
		Optional<Product> service = dao.findById("10");;
		if(service.isPresent()) {
			assertEquals(service.get().getProductId(), "10");
		}
	}
	
	
	@Test
	public void getAllProductservice() {
		Mockito.when(dao.findAll())
		.thenReturn(java.util.stream.Stream.of(new Product(),new Product()).collect(Collectors.toList()));
		
		assertEquals(2, a.getAllProducts().size());
		verify(dao, times(1)).findAll();
		
	}
	
	
			
			public void deleteProductservice() {
				String ServiceId = "10";
				a.deleteProduct(ServiceId);
				
				verify(dao, times(1)).deleteById( ServiceId);
			}

}