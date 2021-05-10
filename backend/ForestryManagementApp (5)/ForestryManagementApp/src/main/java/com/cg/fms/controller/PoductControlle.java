package com.cg.fms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.cg.fms.exception.UserNotFoundException;
import com.cg.fms.jwt.JwtTokenUtil;
import com.cg.fms.service.ProductService;

import io.jsonwebtoken.SignatureException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("productDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class PoductControlle{

	@Autowired
	ProductService productservice;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("{pid}")
	public ResponseEntity<?> getProduct(@PathVariable("pid")String productId,HttpServletRequest request){
		validateToken(request);
		Product p=productservice.getProduct(productId);
		if(p==null)
			return new ResponseEntity<String>("product with "+productId+" not found ",HttpStatus.NOT_FOUND);
	return new ResponseEntity<Product>(p,HttpStatus.OK);
	}
	
	@GetMapping
	public List<Product> getAllProducts(HttpServletRequest request){
		validateToken(request);
		return productservice.getAllProducts();
	}
	
	@PostMapping()
	public String saveProduct(@Valid @RequestBody Product a,HttpServletRequest request) {
		
		if (productservice.addProduct(a) != null) {
			return "New Product added";
		} else {
			return "Product already exists";
		}
	}
	
	
	@PostMapping("{productId}/{productName}/{productDescription}/{productQuantity}")
	public String saveProduct(@PathVariable("productId") String productId, @PathVariable("productName") String productName, @PathVariable("productDescription") String productDescription,
			@PathVariable("productQuantity") String productQuantity,HttpServletRequest request) {
		validateToken(request);
		Product a = new Product();
		a.setProductId(productId);
		a.setProductName(productName);
		a.setProductDescription(productDescription);
		a.setProductQuantity(productQuantity);
		if(productservice.addProduct(a) != null) {
			return "New Product added";
		}
		else {
			return "Product already exists";
		}
	}
	
	@PutMapping()
	public String updateProduct(@Valid @RequestBody Product product,HttpServletRequest request) {
		validateToken(request);

		if (productservice.updateProduct(product))
			return "Product is updated";
		else
			return "Product not found";
	}
	
	
	@PutMapping("{productId}")
	public String updateProduct(@PathVariable("productId") String productId, String productName, String productDescription,
			String productQuantity,HttpServletRequest request) {
		validateToken(request);
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
	
	@DeleteMapping("{productId}")
	public String deleteProduct(@PathVariable("productId") String productId,HttpServletRequest request) {
		validateToken(request);
		if(productservice.deleteProduct(productId))
			return "product deleted";
		return "product not deleted";
	}
	
	public void validateToken(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String jwtToken = null;

		if (tokenHeader == null)
			throw new UserNotFoundException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new UserNotFoundException("Invalid Token");

		jwtToken = tokenHeader.substring(7);
		try {
			if (!(jwtTokenUtil.validateToken(jwtToken)))
				throw new UserNotFoundException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new UserNotFoundException("Invalid Token");
		}
	}
	
}