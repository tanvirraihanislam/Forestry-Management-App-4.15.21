package com.cg.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cg.fms.dto.Order;
@Component
@Repository
public interface OrderDao extends JpaRepository<Order,String>{

}
