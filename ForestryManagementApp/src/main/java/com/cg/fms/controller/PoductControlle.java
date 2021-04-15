package com.cg.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Customer;
import com.cg.fms.dto.Order;
import com.cg.fms.dto.Product;
import com.cg.fms.service.ProductService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("productDetails")
public class PoductControlle{

	@Autowired
	ProductService productservice;
	
	@GetMapping("{pid}")
	public ResponseEntity<?> getProduct(@PathVariable("pid")String productId){
		Product p=productservice.getProduct(productId);
		if(p==null)
			return new ResponseEntity<String>("product with "+productId+" not found ",HttpStatus.NOT_FOUND);
	return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productservice.getAllProducts();
	}
	
	
	@PostMapping("{productId}/{productName}/{productDescription}/{productQuantity}")
	public String saveProduct(@PathVariable("productId") String productId, @PathVariable("productName") String productName, @PathVariable("productDescription") String productDescription,
			@PathVariable("productQuantity") String productQuantity) {
		Product a = new Product();
		a.setProductId(productId);
		a.setProductName(productName);
		a.setProductDescription(productDescription);
		a.setProductQuantity(productQuantity);
		if(productservice.addProduct(a)) {
			return "New Product added";
		}
		else {
			return "Product already exists";
		}
	}
	
	
	@PutMapping("{productId}")
	public String updateProduct(@PathVariable("productId") String productId, String productName, String productDescription,
			String productQuantity) {
		
		Product product = new Product();
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductQuantity(productQuantity);
		
		if(productservice.updateProduct(product))
			return "Product is updated";
		else
			return "Product not found";
	}
	
	
	public String deleteProduct(String productId) {
		if(productservice.deleteProduct(productId))
			return "product deleted";
		return "product not deleted";
	}
	
}