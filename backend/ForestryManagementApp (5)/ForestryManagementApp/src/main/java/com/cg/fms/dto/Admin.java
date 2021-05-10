package com.cg.fms.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="adminDetails")
public class Admin {
	
	@Id
	@Column(name="adminId",length=20)
	private String adminId;
	
	@Column(name="adminName",length=20)
	private String adminName;

	@Column(name="adminPassword",length=20)
	private String adminPassword;
	
	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	private Set<Contract> contract;
	
	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	private Set<Customer> customer;

	
	
	public Admin() {
		super();
	}




	public Admin(String adminId, String adminName, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}




	public String getAdminId() {
		return adminId;
	}



	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return adminName;
	}



	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}



	public String getAdminPassword() {
		return adminPassword;
	}



	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}


	
	public Set<Contract> getContract() {
		return contract;
	}


	@JsonIgnore
	public void setContract(Set<Contract> contract) {
		this.contract = contract;
	}
	
	public Set<Customer> getCustomer() {
		return customer;
	}

	@JsonIgnore
	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword
				+ ", contract=" + contract + ", customer=" + customer + "]";
	}
	
	


	
	
	
	
}
