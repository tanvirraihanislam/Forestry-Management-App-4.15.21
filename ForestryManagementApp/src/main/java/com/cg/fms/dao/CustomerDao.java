package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.fms.dto.Customer;
@Component
@Repository
public interface CustomerDao extends JpaRepository<Customer,String>{
	@Query(value="SELECT c FROM Customer c WHERE c.customerId = :customerId and c.customerPassword = :customerPassword")
	Customer searchPassword(@Param("customerId") String customerId,@Param ("customerPassword") String customerPassword);
}