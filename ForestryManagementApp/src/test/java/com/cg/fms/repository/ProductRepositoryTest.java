package com.cg.fms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.fms.dao.ProductDao;
import com.cg.fms.dto.Product;

@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductDao productrepo;
	private Product product;
	private Product product2;
	
	@BeforeEach
	void setUp() {
		
		product = new Product();
		
		product.setProductId("P01");
		product.setProductName("Perfume");
		product.setProductDescription("Natural fragrance");
		product.setProductQuantity("12");
		product.setOrder(null);
		
		product2 = new Product();
		
		product2.setProductId("P02");
		product2.setProductName("Watch");
		product2.setProductDescription("wrist watch");
		product2.setProductQuantity("15");
		product2.setOrder(null);
		
		
	}
	
	@AfterEach
	void tearDown() {
		productrepo.deleteAll();
		product = null;
		product2 = null;
		
	}
	
	@Test
	public void givenProductIdThenShouldReturnCorrespondingProductDetails() {
		
		Product product1 = productrepo.save(product);
		
		Optional<Product> optional = productrepo.findById(product1.getProductId());
		
		assertEquals(product1.getProductId(), optional.get().getProductId());
		 
	}
	
	@Test
	public void givenProductThenShouldReturnThisProduct() {
		
		productrepo.save(product);
		
		Product fetchedContract = productrepo.findById(product.getProductId()).get();
		
		assertEquals("P01", fetchedContract.getProductId());
		
	}
	
	@Test
	public void givenMultipleProductsThenShouldReturnListOfAllProducts() {
		
		productrepo.save(product);
		productrepo.save(product2);
		
		List<Product> proList = productrepo.findAll();
		
		System.out.println(proList.get(0).getProductDescription());
		
		assertEquals("wrist watch", proList.get(1).getProductDescription());
	
	}
	
	@Test
	public void givenProductIdThenShouldReturnDeletedProduct() {
	
		productrepo.save(product);
		productrepo.deleteById(product.getProductId());
		
		Optional<Product> optional = productrepo.findById(product.getProductId());
		
		assertEquals(Optional.empty(), optional);
	}
	
}
