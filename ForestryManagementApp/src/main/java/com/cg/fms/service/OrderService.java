package com.cg.fms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.fms.dto.Order;
@Service
public interface OrderService {
	
	public Order getOrder(String orderNumber);

	public boolean addOrder(Order order);

	public boolean updateOrder(Order order);

	public boolean deleteOrder(String orderNumber);
	
	public List<Order> getAllOrders();
}
