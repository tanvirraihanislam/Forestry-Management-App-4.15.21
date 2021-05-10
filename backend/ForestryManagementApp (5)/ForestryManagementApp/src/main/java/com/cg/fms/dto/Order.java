package com.cg.fms.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orderDetails")
public class Order {
	
	@Id
	@Column(name="orderNumber",length=20)
	private String orderNumber;

	@Column(name="deliveryPlace",length=20)
	private String deliveryPlace;

	@Column(name="deliveryDate",length=20)
	private String deliveryDate;

	@Column(name="quantity",length=20)
	private String quantity;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="schedulerId")
	private Scheduler scheduler;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="customerId")
	private Customer customer;

	
	@OneToOne
	private Product products;


	public Order() {
		super();
	}


	public Order(String orderNumber, String deliveryPlace, String deliveryDate, String quantity) {
		super();
		this.orderNumber = orderNumber;
		this.deliveryPlace = deliveryPlace;
		this.deliveryDate = deliveryDate;
		this.quantity = quantity;

	}


	public String getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getDeliveryPlace() {
		return deliveryPlace;
	}


	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}


	public String getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public Scheduler getScheduler() {
		return scheduler;
	}


	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	



	public Product getProducts() {
		return products;
	}


	public void setProducts(Product products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", deliveryPlace=" + deliveryPlace + ", deliveryDate="
				+ deliveryDate + ", quantity=" + quantity + ", scheduler=" + scheduler + ", customer=" + customer
				+ ", products=" + products + "]";
	}


	
	
}
