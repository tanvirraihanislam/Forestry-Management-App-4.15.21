package com.cg.fms.service;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.fms.dto.Product;
@Service
public interface ProductService  {
	public Product getProduct(String productId);

	public Product addProduct(Product product);

	public boolean updateProduct(Product product);

	public boolean deleteProduct(String productId);
	
	public List<Product> getAllProducts();
	
}