package com.cg.fms.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="productDetails")
public class Product {
	
	@Id
	@Column(name="productId",length=20)
	private String productId;

	@Column(name="productName",length=20)
	private String productName;

	@Column(name="productDescription",length=20)
	private String productDescription;

	@Column(name="productQuantity",length=20)
	private String productQuantity;
	
	@OneToOne(mappedBy="products")
	@JsonIgnore
	private Order order;

	public Product() {
		super();
	}

	public Product(String productId, String productName, String productDescription, String productQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", productQuantity=" + productQuantity + ", order=" + order + "]";
	}
	
	


	
	
	
}
