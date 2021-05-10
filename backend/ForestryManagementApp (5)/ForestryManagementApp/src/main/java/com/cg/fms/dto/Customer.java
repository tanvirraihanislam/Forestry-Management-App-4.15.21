package com.cg.fms.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customerDetails")
public class Customer {
	
	@Id
	@Column(name="customerId", length=20)
	private String customerId;
	
	@Column(name="customerName",length=20)
	private String customerName;
	
	@Column(name="customerType",length=20)
	private String customerType;
	

	@Column(name="customerPassword",length=20)
	private String customerPassword;

	@Column(name="customerEmail",length=20)
	private String customerEmail;
	
	@Column(name="customerAddress",length=20)
	private String customerAddress;

	@Column(name="customerTown",length=20)
	private String customerTown;	

	@Column(name="customerPostalCode",length=20)
	private String customerPostalCode;
	
	@Column(name="customerContact",length=20)
	private String customerContact;

	@OneToMany(mappedBy="customer")
	private Set<Contract> contract;

	@OneToMany(mappedBy="customer")
	private Set<Order> order;
	
	@ManyToOne
	@JoinColumn(name="schedulerId")
	private Scheduler scheduler;
	
	@ManyToOne
	@JoinColumn(name="adminId")
	private Admin admin;

	public Customer() {
		super();
	}

	public Customer(String customerId, String customerName, String customerType, String customerPassword,
			String customerEmail, String customerAddress, String customerTown, String customerPostalCode,
			String customerContact) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerType = customerType;
		this.customerPassword = customerPassword;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.customerTown = customerTown;
		this.customerPostalCode = customerPostalCode;
		this.customerContact = customerContact;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerTown() {
		return customerTown;
	}

	public void setCustomerTown(String customerTown) {
		this.customerTown = customerTown;
	}

	public String getCustomerPostalCode() {
		return customerPostalCode;
	}

	public void setCustomerPostalCode(String customerPostalCode) {
		this.customerPostalCode = customerPostalCode;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public Set<Contract> getContract() {
		return contract;
	}

	public void setContract(Set<Contract> contract) {
		this.contract = contract;
	}

	public Set<Order> getOrder() {
		return order;
	}

	public void setOrder(Set<Order> order) {
		this.order = order;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	


	

	
}
