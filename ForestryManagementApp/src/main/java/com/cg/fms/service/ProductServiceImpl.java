package com.cg.fms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.fms.dao.ProductDao;
import com.cg.fms.dto.Product;
import com.cg.fms.exception.ProductException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
	ProductDao ado;
	@Override
	public Product getProduct(String productId) {
		// TODO Auto-generated method stub
		Optional<Product> opt=ado.findById(productId);
		if(opt.isPresent())
			return opt.get();
		return null;
		}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		if(ado.existsById(product.getProductId())) {
			throw new ProductException("product with number "+product.getProductId()+" already exists");
		}else {
			Product savedProduct = ado.save(product);
			return savedProduct;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
				if(ado.existsById(product.getProductId())) {
					ado.save(product);
					return true;
				}
				return false;	}

	@Override
	public boolean deleteProduct(String productId) {
		// TODO Auto-generated method stub
		if(ado.existsById(productId)) {
			ado.deleteById(productId);
			return true;
		}
		return false;	
		}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return (List<Product>)ado.findAll();
	}

}
